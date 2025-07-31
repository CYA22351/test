package controller;

import entity.User;
import entity.Product;
import entity.Category;
import service.ProductService;
import service.impl.ProductServiceImpl;
import util.DBUtil;
import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 商品列表页面
            listProducts(request, response);
        } else if (pathInfo.equals("/add")) {
            // 添加商品页面
            showAddForm(request, response);
        } else if (pathInfo.equals("/edit")) {
            // 编辑商品页面
            showEditForm(request, response);
        } else if (pathInfo.equals("/delete")) {
            // 删除商品
            deleteProduct(request, response);
        } else if (pathInfo.equals("/shelf")) {
            // 货架管理页面
            showShelfForm(request, response);
        } else if (pathInfo.equals("/stock")) {
            // 库存管理页面
            showStockForm(request, response);
        } else if (pathInfo.equals("/lowstock")) {
            // 库存不足商品
            showLowStock(request, response);
        } else if (pathInfo.equals("/search")) {
            // 商品搜索
            searchProducts(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 添加商品
            addProduct(request, response);
        } else if (pathInfo.equals("/edit")) {
            // 更新商品
            updateProduct(request, response);
        } else if (pathInfo.equals("/shelf")) {
            // 货架操作
            shelfOperation(request, response);
        } else if (pathInfo.equals("/stock")) {
            // 库存操作
            stockOperation(request, response);
        } else if (pathInfo.equals("/price")) {
            // 价格调整
            updatePrice(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查权限
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !hasProductPermission(currentUser)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String categoryIdStr = request.getParameter("categoryId");
        String statusStr = request.getParameter("status");

        List<Product> products;
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                products = productService.findByCategory(categoryId);
            } catch (NumberFormatException e) {
                products = productService.findAll();
            }
        } else if (statusStr != null && !statusStr.isEmpty()) {
            try {
                int status = Integer.parseInt(statusStr);
                products = productService.findByStatus(status);
            } catch (NumberFormatException e) {
                products = productService.findAll();
            }
        } else {
            products = productService.findAll();
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/product-add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product product = productService.findById(id);
                if (product != null) {
                    List<Category> categories = categoryDAO.findAll();
                    request.setAttribute("product", product);
                    request.setAttribute("categories", categories);
                    request.getRequestDispatcher("/product-edit.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/product");
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String barcode = request.getParameter("barcode");
        String categoryIdStr = request.getParameter("categoryId");
        String priceStr = request.getParameter("price");
        String costPriceStr = request.getParameter("costPrice");
        String shelfCode = request.getParameter("shelfCode");
        String stockStr = request.getParameter("stock");
        String minStockStr = request.getParameter("minStock");

        if (name != null && barcode != null && priceStr != null) {
            try {
                Product product = new Product(name, barcode, new java.math.BigDecimal(priceStr));

                if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                    product.setCategoryId(Integer.parseInt(categoryIdStr));
                }
                if (costPriceStr != null && !costPriceStr.isEmpty()) {
                    product.setCostPrice(new java.math.BigDecimal(costPriceStr));
                }
                if (shelfCode != null && !shelfCode.isEmpty()) {
                    product.setShelfCode(shelfCode);
                }
                if (stockStr != null && !stockStr.isEmpty()) {
                    product.setStock(Integer.parseInt(stockStr));
                }
                if (minStockStr != null && !minStockStr.isEmpty()) {
                    product.setMinStock(Integer.parseInt(minStockStr));
                }

                if (productService.addProduct(product)) {
                    request.setAttribute("message", "商品添加成功");
                } else {
                    request.setAttribute("error", "商品添加失败，条码可能已存在");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "价格或数量格式错误");
            }
        } else {
            request.setAttribute("error", "请填写完整信息");
        }

        response.sendRedirect(request.getContextPath() + "/product");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String barcode = request.getParameter("barcode");
        String categoryIdStr = request.getParameter("categoryId");
        String priceStr = request.getParameter("price");
        String costPriceStr = request.getParameter("costPrice");
        String shelfCode = request.getParameter("shelfCode");
        String stockStr = request.getParameter("stock");
        String minStockStr = request.getParameter("minStock");
        String statusStr = request.getParameter("status");

        if (idStr != null && name != null && barcode != null && priceStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product product = productService.findById(id);
                if (product != null) {
                    product.setName(name);
                    product.setBarcode(barcode);
                    if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                        product.setCategoryId(Integer.parseInt(categoryIdStr));
                    }
                    if (priceStr != null && !priceStr.isEmpty()) {
                        product.setPrice(new java.math.BigDecimal(priceStr));
                    }
                    if (costPriceStr != null && !costPriceStr.isEmpty()) {
                        product.setCostPrice(new java.math.BigDecimal(costPriceStr));
                    }
                    if (shelfCode != null && !shelfCode.isEmpty()) {
                        product.setShelfCode(shelfCode);
                    }
                    if (stockStr != null && !stockStr.isEmpty()) {
                        product.setStock(Integer.parseInt(stockStr));
                    }
                    if (minStockStr != null && !minStockStr.isEmpty()) {
                        product.setMinStock(Integer.parseInt(minStockStr));
                    }
                    if (statusStr != null && !statusStr.isEmpty()) {
                        product.setStatus(Integer.parseInt(statusStr));
                    }
                    productService.updateProduct(product);
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/product");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                if (productService.deleteProduct(id)) {
                    request.setAttribute("message", "商品删除成功");
                } else {
                    request.setAttribute("error", "商品删除失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/product/");
    }

    private void showShelfForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product product = productService.findById(id);
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/product-shelf.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/product/");
    }

    private void shelfOperation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String operation = request.getParameter("operation");
        String shelfCode = request.getParameter("shelfCode");

        if (idStr != null && operation != null) {
            try {
                int id = Integer.parseInt(idStr);
                boolean success = false;

                switch (operation) {
                    case "up":
                        if (shelfCode != null && !shelfCode.isEmpty()) {
                            success = productService.putOnShelf(id, shelfCode);
                        }
                        break;
                    case "down":
                        success = productService.takeOffShelf(id);
                        break;
                    case "move":
                        if (shelfCode != null && !shelfCode.isEmpty()) {
                            success = productService.moveShelf(id, shelfCode);
                        }
                        break;
                }

                if (success) {
                    request.setAttribute("message", "货架操作成功");
                } else {
                    request.setAttribute("error", "货架操作失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/product/");
    }

    private void showStockForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product product = productService.findById(id);
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/product-stock.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                // 忽略
            }
        }
        response.sendRedirect(request.getContextPath() + "/product/");
    }

    private void stockOperation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String operation = request.getParameter("operation");
        String quantityStr = request.getParameter("quantity");

        if (idStr != null && operation != null && quantityStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                int quantity = Integer.parseInt(quantityStr);
                boolean success = false;

                switch (operation) {
                    case "increase":
                        success = productService.increaseStock(id, quantity);
                        break;
                    case "decrease":
                        success = productService.reduceStock(id, quantity);
                        break;
                    case "set":
                        success = productService.updateStock(id, quantity);
                        break;
                }

                if (success) {
                    request.setAttribute("message", "库存操作成功");
                } else {
                    request.setAttribute("error", "库存操作失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "参数错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/product/");
    }

    private void updatePrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String priceStr = request.getParameter("price");

        if (idStr != null && priceStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                BigDecimal price = new BigDecimal(priceStr);

                if (productService.updatePrice(id, price)) {
                    request.setAttribute("message", "价格更新成功");
                } else {
                    request.setAttribute("error", "价格更新失败");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "价格格式错误");
            }
        }

        response.sendRedirect(request.getContextPath() + "/product/");
    }

    private void showLowStock(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> lowStockProducts = productService.findLowStock();
        request.setAttribute("products", lowStockProducts);
        request.setAttribute("isLowStock", true);
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String categoryIdStr = request.getParameter("categoryId");

        // 这里应该实现搜索逻辑，暂时返回所有商品
        List<Product> products = productService.findAll();

        // 简单的关键词过滤
        if (keyword != null && !keyword.isEmpty()) {
            products.removeIf(product -> !product.getName().toLowerCase().contains(keyword.toLowerCase()) &&
                    !product.getBarcode().contains(keyword));
        }

        request.setAttribute("products", products);
        request.setAttribute("keyword", keyword);
        request.getRequestDispatcher("/product.jsp").forward(request, response);
    }

    private boolean hasProductPermission(User user) {
        return "admin".equals(user.getRole()) || "product".equals(user.getRole());
    }

    public int getLowStockCount() {
        String sql = "SELECT COUNT(*) FROM product WHERE stock <= min_stock";
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}