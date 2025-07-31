package controller;

import entity.User;
import entity.Sale;
import entity.Product;
import entity.Member;
import service.SaleService;
import service.ProductService;
import service.MemberService;
import service.impl.SaleServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cashier/*")
public class CashierController extends HttpServlet {

    private SaleService saleService = new SaleServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String saleIdStr = request.getParameter("saleId");
        // 1. /cashier 或 /cashier/ 直接新建单据
        if (pathInfo == null || pathInfo.equals("/")) {
            if (saleIdStr == null || saleIdStr.isEmpty()) {
                // 自动新建单据并重定向
                Sale sale = autoCreateSale(request, response);
                if (sale != null) {
                    response.sendRedirect(request.getContextPath() + "/cashier?saleId=" + sale.getId());
                } else {
                    request.setAttribute("error", "创建销售单失败，请重试或联系管理员");
                    request.getRequestDispatcher("/cashier.jsp").forward(request, response);
                }
                return;
            } else {
                showCashier(request, response);
                return;
            }
        } else if (pathInfo.equals("/new")) {
            // 2. /cashier/new 始终新建单据
            if (!"GET".equalsIgnoreCase(request.getMethod())) {
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                return;
            }
            Sale sale = autoCreateSale(request, response);
            if (sale != null) {
                response.sendRedirect(request.getContextPath() + "/cashier?saleId=" + sale.getId());
            } else {
                request.setAttribute("error", "创建销售单失败，请重试或联系管理员");
                request.getRequestDispatcher("/cashier.jsp").forward(request, response);
            }
            return;
        } else if (pathInfo.equals("/hold")) {
            showHoldSales(request, response);
            return;
        } else if (pathInfo.equals("/resume")) {
            resumeSale(request, response);
            return;
        } else if (pathInfo.equals("/receipt")) {
            printReceipt(request, response);
            return;
        } else if (pathInfo.equals("/product")) {
            findProduct(request, response);
            return;
        } else if (pathInfo.equals("/member")) {
            findMember(request, response);
            return;
        } else if (pathInfo.equals("/searchMember")) {
            String cardNo = request.getParameter("cardNo");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            entity.Member member = memberService.findByCardNo(cardNo);
            if (member != null && (member.getStatus() == 1 || member.getStatus() == 0)) {
                response.getWriter()
                        .write("{\"success\":true,\"id\":" + member.getId() + ",\"name\":\"" + member.getName()
                                + "\",\"level\":\"" + member.getLevel() + "\",\"points\":" + member.getPoints() + "}");
            } else {
                response.getWriter().write("{\"success\":false,\"message\":\"会员不存在或已禁用\"}");
            }
            return;
        } else if (pathInfo.equals("/print")) {
            printReceipt(request, response);
            return;
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 添加商品到销售单
            addProduct(request, response);
        } else if (pathInfo.equals("/setMember")) {
            String saleIdStr = request.getParameter("saleId");
            String cardNo = request.getParameter("cardNo");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (saleIdStr != null && cardNo != null) {
                try {
                    int saleId = Integer.parseInt(saleIdStr);
                    boolean ok = saleService.setMember(saleId, cardNo);
                    if (ok) {
                        response.getWriter().write("{\"success\":true}");
                    } else {
                        response.getWriter().write("{\"success\":false,\"message\":\"会员设置失败\"}");
                    }
                } catch (Exception e) {
                    response.getWriter().write("{\"success\":false,\"message\":\"参数错误\"}");
                }
            } else {
                response.getWriter().write("{\"success\":false,\"message\":\"参数缺失\"}");
            }
            return;
        } else if (pathInfo.equals("/updateQuantity")) {
            // 商品数量变更
            String saleIdStr = request.getParameter("saleId");
            String productIdStr = request.getParameter("productId");
            String changeStr = request.getParameter("change");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                int saleId = Integer.parseInt(saleIdStr);
                int productId = Integer.parseInt(productIdStr);
                int change = Integer.parseInt(changeStr);
                Sale sale = saleService.findById(saleId);
                if (sale == null)
                    throw new Exception("无效订单");
                List<entity.SaleItem> items = sale.getItems();
                int newQty = 0;
                for (entity.SaleItem item : items) {
                    if (item.getProductId() == productId) {
                        newQty = item.getQuantity() + change;
                        break;
                    }
                }
                if (newQty <= 0) {
                    // 删除该商品
                    boolean ok = saleService.removeProductFromSale(saleId, productId);
                    response.getWriter().write("{\"success\":" + ok + "}");
                } else {
                    boolean ok = saleService.updateProductQuantity(saleId, productId, newQty);
                    response.getWriter().write("{\"success\":" + ok + "}");
                }
            } catch (Exception e) {
                response.getWriter().write("{\"success\":false,\"message\":\"参数错误\"}");
            }
            return;
        } else if (pathInfo.equals("/removeItem")) {
            // 删除商品
            String saleIdStr = request.getParameter("saleId");
            String productIdStr = request.getParameter("productId");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                int saleId = Integer.parseInt(saleIdStr);
                int productId = Integer.parseInt(productIdStr);
                boolean ok = saleService.removeProductFromSale(saleId, productId);
                response.getWriter().write("{\"success\":" + ok + "}");
            } catch (Exception e) {
                response.getWriter().write("{\"success\":false,\"message\":\"参数错误\"}");
            }
            return;
        } else if (pathInfo.equals("/checkout")) {
            // 结账
            String saleIdStr = request.getParameter("saleId");
            String paymentMethod = request.getParameter("paymentMethod");
            String paymentAmount = request.getParameter("paymentAmount");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                int saleId = Integer.parseInt(saleIdStr);
                boolean ok = saleService.checkout(saleId, paymentMethod);
                response.getWriter().write("{\"success\":" + ok + "}");
            } catch (Exception e) {
                response.getWriter().write("{\"success\":false,\"message\":\"结账失败\"}");
            }
            return;
        } else if (pathInfo.equals("/hold")) {
            holdSale(request, response);
        } else if (pathInfo.equals("/cancel")) {
            cancelSale(request, response);
        } else if (pathInfo.equals("/clear")) {
            // 清空购物车
            String saleIdStr = request.getParameter("saleId");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                int saleId = Integer.parseInt(saleIdStr);
                saleService.cancelSale(saleId);
                response.getWriter().write("{\"success\":true}");
            } catch (Exception e) {
                response.getWriter().write("{\"success\":false,\"message\":\"清空失败\"}");
            }
            return;
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showCashier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null
                || !("cashier".equals(currentUser.getRole()) || "admin".equals(currentUser.getRole()))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=unauthorized");
            return;
        }

        String saleIdStr = request.getParameter("saleId");
        if (saleIdStr == null || saleIdStr.isEmpty()) {
            // 没有saleId，自动新建
            Sale sale = autoCreateSale(request, response);
            if (sale != null && sale.getId() > 0) {
                response.sendRedirect(request.getContextPath() + "/cashier?saleId=" + sale.getId());
            } else {
                request.setAttribute("error", "创建销售单失败，请重试或联系管理员");
                request.getRequestDispatcher("/cashier.jsp").forward(request, response);
            }
            return;
        }
        int saleId = 0;
        try {
            saleId = Integer.parseInt(saleIdStr);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/cashier");
            return;
        }
        if (saleId <= 0) {
            response.sendRedirect(request.getContextPath() + "/cashier");
            return;
        }
        Sale sale = saleService.findById(saleId);
        if (sale == null) {
            response.sendRedirect(request.getContextPath() + "/cashier");
            return;
        }
        List<entity.SaleItem> cartItems = new java.util.ArrayList<>();
        java.math.BigDecimal subtotal = java.math.BigDecimal.ZERO;
        if (sale.getItems() != null) {
            for (entity.SaleItem item : sale.getItems()) {
                if (item.getQuantity() > 0) {
                    cartItems.add(item);
                    subtotal = subtotal.add(item.getAmount());
                }
            }
        }
        request.setAttribute("currentSale", sale);
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("currentSaleId", saleId);
        // 会员信息
        entity.Member currentMember = null;
        java.math.BigDecimal discount = java.math.BigDecimal.ZERO;
        String memberDiscount = "无";
        if (sale.getMemberId() != null) {
            currentMember = memberService.findById(sale.getMemberId());
            if (currentMember != null) {
                discount = memberService.calculateDiscount(currentMember.getId(), subtotal);
                memberDiscount = getDiscountDisplay(currentMember.getLevel());
            }
        }
        request.setAttribute("currentMember", currentMember);
        request.setAttribute("discount", discount);
        // 应付金额 = 商品总额 - 折扣
        java.math.BigDecimal totalAmount = subtotal.subtract(discount);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("memberDiscount", memberDiscount);
        request.getRequestDispatcher("/cashier.jsp").forward(request, response);
    }

    private Sale autoCreateSale(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return null;
        }
        Sale sale = saleService.createSale(currentUser.getId());
        if (sale == null || sale.getId() <= 0) {
            return null;
        }
        return sale;
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null
                || !("cashier".equals(currentUser.getRole()) || "admin".equals(currentUser.getRole()))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=unauthorized");
            return;
        }
        String saleIdStr = request.getParameter("saleId");
        String barcodeOrName = request.getParameter("barcode");
        String quantityStr = request.getParameter("quantity");
        int saleId = 0;
        if (saleIdStr != null) {
            try {
                saleId = Integer.parseInt(saleIdStr);
            } catch (NumberFormatException e) {
                saleId = 0;
            }
        }
        if (barcodeOrName != null && quantityStr != null) {
            try {
                int quantity = Integer.parseInt(quantityStr);
                // 先查条码
                Product product = productService.findByBarcode(barcodeOrName);
                if (product == null) {
                    // 再查唯一名称
                    List<Product> products = productService.searchByName(barcodeOrName);
                    if (products != null && products.size() == 1) {
                        product = products.get(0);
                    } else if (products != null && products.size() > 1) {
                        // 多个商品，友好提示
                        request.getSession().setAttribute("message", "有多个商品匹配，请输入更精确的名称或用条码！");
                        request.getSession().setAttribute("messageType", "error");
                        response.sendRedirect(request.getContextPath() + "/cashier/?saleId=" + saleId);
                        return;
                    }
                }
                if (product == null) {
                    // 没有该商品，直接跳转，不提示
                    response.sendRedirect(request.getContextPath() + "/cashier/?saleId=" + saleId);
                    return;
                }
                if (saleId == 0) {
                    // 新建销售单
                    Sale sale = saleService.createSale(currentUser.getId());
                    saleId = sale.getId();
                }
                if (saleService.addProductToSale(saleId, product.getBarcode(), quantity)) {
                    response.sendRedirect(request.getContextPath() + "/cashier/?saleId=" + saleId);
                    return;
                } else {
                    request.getSession().setAttribute("message", "商品添加失败，库存不足或状态异常");
                    request.getSession().setAttribute("messageType", "error");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("message", "参数错误");
                request.getSession().setAttribute("messageType", "error");
            }
        }
        response.sendRedirect(request.getContextPath() + "/cashier/?saleId=" + saleId);
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String saleIdStr = request.getParameter("saleId");
        String payType = request.getParameter("payType");

        if (saleIdStr != null && payType != null) {
            try {
                int saleId = Integer.parseInt(saleIdStr);

                Sale sale = saleService.findById(saleId);
                if (sale != null && "PENDING".equals(sale.getStatus())) {
                    if (saleService.checkout(saleId, payType)) {
                        request.setAttribute("message", "结账成功");
                        // 跳转到小票页面
                        response.sendRedirect(request.getContextPath() + "/cashier/receipt?saleId=" + saleId);
                        return;
                    } else {
                        request.setAttribute("error", "结账失败");
                    }
                } else {
                    request.setAttribute("error", "销售单状态不正确，无法结算");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/cashier/?saleId=" + saleIdStr);
    }

    private void holdSale(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String saleIdStr = request.getParameter("saleId");

        if (saleIdStr != null) {
            try {
                int saleId = Integer.parseInt(saleIdStr);

                if (saleService.holdSale(saleId)) {
                    request.setAttribute("message", "挂单成功");
                } else {
                    request.setAttribute("error", "挂单失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/cashier/");
    }

    private void cancelSale(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String saleIdStr = request.getParameter("saleId");

        if (saleIdStr != null) {
            try {
                int saleId = Integer.parseInt(saleIdStr);

                if (saleService.cancelSale(saleId)) {
                    request.setAttribute("message", "撤单成功");
                } else {
                    request.setAttribute("error", "撤单失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/cashier/");
    }

    private void showHoldSales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<Sale> holdSales = saleService.getHoldSales(currentUser.getId());
        request.setAttribute("holdSales", holdSales);
        request.getRequestDispatcher("/cashier-hold.jsp").forward(request, response);
    }

    private void resumeSale(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String saleIdStr = request.getParameter("saleId");

        if (saleIdStr != null) {
            try {
                int saleId = Integer.parseInt(saleIdStr);

                if (saleService.resumeSale(saleId)) {
                    request.setAttribute("message", "恢复挂单成功");
                    response.sendRedirect(request.getContextPath() + "/cashier/?saleId=" + saleId);
                    return;
                } else {
                    request.setAttribute("error", "恢复挂单失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/cashier/hold");
    }

    private void printReceipt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String saleIdStr = request.getParameter("saleId");

        if (saleIdStr != null) {
            try {
                int saleId = Integer.parseInt(saleIdStr);
                Sale sale = saleService.findById(saleId);
                if (sale != null) {
                    String receipt = saleService.generateReceipt(saleId);
                    request.setAttribute("receipt", receipt);
                    request.setAttribute("sale", sale);
                    request.getRequestDispatcher("/cashier-receipt.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }

        response.sendRedirect(request.getContextPath() + "/cashier/");
    }

    private void findProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String barcode = request.getParameter("barcode");

        if (barcode != null) {
            Product product = productService.findByBarcode(barcode);
            if (product != null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(String.format(
                        "{\"id\":%d,\"name\":\"%s\",\"price\":%.2f,\"stock\":%d}",
                        product.getId(), product.getName(), product.getPrice(), product.getStock()));
                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    private void findMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cardNo = request.getParameter("cardNo");

        if (cardNo != null) {
            Member member = memberService.findByCardNo(cardNo);
            if (member != null) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(String.format(
                        "{\"id\":%d,\"name\":\"%s\",\"level\":\"%s\",\"points\":%d}",
                        member.getId(), member.getName(), member.getLevel(), member.getPoints()));
                return;
            }
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    private String getDiscountDisplay(String level) {
        switch (level) {
            case "PLATINUM":
                return "8.0";
            case "GOLD":
                return "8.5";
            case "SILVER":
                return "9.0";
            case "BRONZE":
                return "9.5";
            default:
                return "无";
        }
    }

    private boolean hasCashierPermission(User user) {
        return "admin".equals(user.getRole()) || "cashier".equals(user.getRole());
    }
}