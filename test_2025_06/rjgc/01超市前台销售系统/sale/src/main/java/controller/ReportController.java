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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@WebServlet("/report/*")
public class ReportController extends HttpServlet {

    private SaleService saleService = new SaleServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // 报表首页
            showReportIndex(request, response);
        } else if (pathInfo.equals("/sales")) {
            // 销售报表
            showSalesReport(request, response);
        } else if (pathInfo.equals("/product")) {
            // 商品报表
            showProductReport(request, response);
        } else if (pathInfo.equals("/member")) {
            // 会员报表
            showMemberReport(request, response);
        } else if (pathInfo.equals("/cashier")) {
            // 收银员报表
            showCashierReport(request, response);
        } else if (pathInfo.equals("/daily")) {
            // 日报表
            showDailyReport(request, response);
        } else if (pathInfo.equals("/monthly")) {
            // 月报表
            showMonthlyReport(request, response);
        } else if (pathInfo.equals("/export")) {
            // 导出报表
            exportReport(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo.equals("/filter")) {
            // 筛选报表
            filterReport(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showReportIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 检查权限
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || !hasReportPermission(currentUser)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 获取基础统计数据
        Map<String, Object> stats = new HashMap<>();

        // 今日销售统计
        BigDecimal todaySales = saleService.getTodaySales();
        int todayOrders = saleService.getTodayOrderCount();
        stats.put("todaySales", todaySales);
        stats.put("todayOrders", todayOrders);

        // 本月销售统计
        BigDecimal monthSales = saleService.getMonthSales();
        int monthOrders = saleService.getMonthOrderCount();
        stats.put("monthSales", monthSales);
        stats.put("monthOrders", monthOrders);

        // 商品统计
        int totalProducts = productService.getTotalCount();
        int lowStockProducts = productService.getLowStockCount();
        stats.put("totalProducts", totalProducts);
        stats.put("lowStockProducts", lowStockProducts);

        // 会员统计
        int totalMembers = memberService.getTotalCount();
        int activeMembers = memberService.getActiveCount();
        stats.put("totalMembers", totalMembers);
        stats.put("activeMembers", activeMembers);

        // 实时查询所有销售单数据
        List<Sale> sales = saleService.findAll();
        System.out.println("查到订单数: " + sales.size());
        for (Sale s : sales) {
            System.out.println(s.getSaleNo() + " " + s.getStatus() + " " + s.getTotalAmount());
        }
        request.setAttribute("sales", sales);

        request.setAttribute("stats", stats);
        request.getRequestDispatcher("/report.jsp").forward(request, response);
    }

    private void showSalesReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cashierIdStr = request.getParameter("cashierId");

        List<Sale> sales;
        if (startDate != null && endDate != null) {
            sales = saleService.findByDateRange(startDate, endDate);
        } else if (cashierIdStr != null && !cashierIdStr.isEmpty()) {
            try {
                int cashierId = Integer.parseInt(cashierIdStr);
                sales = saleService.findByCashier(cashierId);
            } catch (NumberFormatException e) {
                sales = saleService.findAll();
            }
        } else {
            sales = saleService.findAll();
        }

        // 计算统计信息
        BigDecimal totalAmount = sales.stream()
                .map(Sale::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        int totalOrders = sales.size();

        request.setAttribute("sales", sales);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("totalOrders", totalOrders);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        request.setAttribute("cashierId", cashierIdStr);

        request.getRequestDispatcher("/report-sales.jsp").forward(request, response);
    }

    private void showProductReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryIdStr = request.getParameter("categoryId");
        String sortBy = request.getParameter("sortBy");

        List<Product> products;
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                products = productService.findByCategory(categoryId);
            } catch (NumberFormatException e) {
                products = productService.findAll();
            }
        } else {
            products = productService.findAll();
        }

        // 排序
        if ("sales".equals(sortBy)) {
            // 按销量排序（这里需要实现销量统计）
            products.sort((p1, p2) -> {
                int sales1 = saleService.getProductSalesCount(p1.getId());
                int sales2 = saleService.getProductSalesCount(p2.getId());
                return Integer.compare(sales2, sales1);
            });
        } else if ("stock".equals(sortBy)) {
            products.sort((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()));
        } else if ("price".equals(sortBy)) {
            products.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        }

        // 计算库存总价值
        BigDecimal totalValue = products.stream()
                .map(p -> p.getPrice().multiply(new BigDecimal(p.getStock())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        request.setAttribute("products", products);
        request.setAttribute("totalValue", totalValue);
        request.setAttribute("categoryId", categoryIdStr);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("/report-product.jsp").forward(request, response);
    }

    private void showMemberReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String level = request.getParameter("level");
        String sortBy = request.getParameter("sortBy");

        List<Member> members;
        if (level != null && !level.isEmpty()) {
            members = memberService.findByLevel(level);
        } else {
            members = memberService.findAll();
        }

        // 排序
        if ("points".equals(sortBy)) {
            members.sort((m1, m2) -> Integer.compare(m2.getPoints(), m1.getPoints()));
        } else if ("consumption".equals(sortBy)) {
            // 按消费金额排序
            members.sort((m1, m2) -> {
                BigDecimal consumption1 = saleService.getMemberConsumption(m1.getId());
                BigDecimal consumption2 = saleService.getMemberConsumption(m2.getId());
                return consumption2.compareTo(consumption1);
            });
        }

        // 计算统计信息
        int totalPoints = members.stream().mapToInt(Member::getPoints).sum();
        BigDecimal totalConsumption = members.stream()
                .map(m -> saleService.getMemberConsumption(m.getId()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        request.setAttribute("members", members);
        request.setAttribute("totalPoints", totalPoints);
        request.setAttribute("totalConsumption", totalConsumption);
        request.setAttribute("level", level);
        request.setAttribute("sortBy", sortBy);

        request.getRequestDispatcher("/report-member.jsp").forward(request, response);
    }

    private void showCashierReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // 获取收银员销售统计
        List<Map<String, Object>> cashierStats = saleService.getCashierSalesStats(startDate, endDate);

        request.setAttribute("cashierStats", cashierStats);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        request.getRequestDispatcher("/report-cashier.jsp").forward(request, response);
    }

    private void showDailyReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");
        if (date == null) {
            date = java.time.LocalDate.now().toString();
        }

        // 获取日报表数据
        Map<String, Object> dailyStats = saleService.getDailyStats(date);

        request.setAttribute("dailyStats", dailyStats);
        request.setAttribute("date", date);

        request.getRequestDispatcher("/report-daily.jsp").forward(request, response);
    }

    private void showMonthlyReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String year = request.getParameter("year");
        String month = request.getParameter("month");

        if (year == null) {
            year = String.valueOf(java.time.Year.now().getValue());
        }
        if (month == null) {
            month = String.valueOf(java.time.MonthDay.now().getMonthValue());
        }

        // 获取月报表数据
        Map<String, Object> monthlyStats = saleService.getMonthlyStats(year, month);

        request.setAttribute("monthlyStats", monthlyStats);
        request.setAttribute("year", year);
        request.setAttribute("month", month);

        request.getRequestDispatcher("/report-monthly.jsp").forward(request, response);
    }

    private void filterReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportType = request.getParameter("reportType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String categoryId = request.getParameter("categoryId");
        String cashierId = request.getParameter("cashierId");

        String redirectUrl = request.getContextPath() + "/report/" + reportType + "?";

        if (startDate != null && !startDate.isEmpty()) {
            redirectUrl += "startDate=" + startDate + "&";
        }
        if (endDate != null && !endDate.isEmpty()) {
            redirectUrl += "endDate=" + endDate + "&";
        }
        if (categoryId != null && !categoryId.isEmpty()) {
            redirectUrl += "categoryId=" + categoryId + "&";
        }
        if (cashierId != null && !cashierId.isEmpty()) {
            redirectUrl += "cashierId=" + cashierId + "&";
        }

        // 移除最后的&
        if (redirectUrl.endsWith("&")) {
            redirectUrl = redirectUrl.substring(0, redirectUrl.length() - 1);
        }

        response.sendRedirect(redirectUrl);
    }

    private void exportReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reportType = request.getParameter("type");
        String format = request.getParameter("format");

        if ("csv".equals(format)) {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=report.csv");

            // 这里应该实现CSV导出逻辑
            response.getWriter().write("Report data in CSV format");
        } else if ("excel".equals(format)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=report.xls");

            // 这里应该实现Excel导出逻辑
            response.getWriter().write("Report data in Excel format");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unsupported format");
        }
    }

    private boolean hasReportPermission(User user) {
        return "admin".equals(user.getRole()) || "manager".equals(user.getRole());
    }
}