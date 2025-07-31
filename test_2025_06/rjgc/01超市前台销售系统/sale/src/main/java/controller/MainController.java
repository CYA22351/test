package controller;

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
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/main")
public class MainController extends HttpServlet {
    private SaleService saleService = new SaleServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> stats = new HashMap<>();
        // 今日日期
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = sdf.parse(today + " 00:00:00");
            endDate = sdf.parse(today + " 23:59:59");
        } catch (Exception e) {
            startDate = new Date();
            endDate = new Date();
        }
        // 今日销售额（只统计已结算订单）
        BigDecimal todaySales = saleService.getTotalSales(startDate, endDate);
        // 今日订单数（只统计已结算订单）
        int todayOrders = saleService.getSaleCount(startDate, endDate);
        // 商品总数
        int totalProducts = productService.getTotalCount();
        // 会员总数
        int totalMembers = memberService.getTotalCount();
        stats.put("todaySales", todaySales);
        stats.put("todayOrders", todayOrders);
        stats.put("totalProducts", totalProducts);
        stats.put("totalMembers", totalMembers);
        request.setAttribute("stats", stats);
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}