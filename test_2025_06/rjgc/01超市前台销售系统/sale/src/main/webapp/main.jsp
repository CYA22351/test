<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <% if (request.getAttribute("stats")==null) { response.sendRedirect("/sale_system_war_exploded/main"); return; }
            %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>超市销售管理系统</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 0;
                        padding: 0;
                        background-color: #f4f4f4;
                    }

                    .header {
                        background-color: #333;
                        color: white;
                        padding: 15px;
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }

                    .header h1 {
                        margin: 0;
                    }

                    .user-info {
                        display: flex;
                        align-items: center;
                        gap: 15px;
                    }

                    .logout-btn {
                        background-color: #f44336;
                        color: white;
                        padding: 8px 15px;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                        text-decoration: none;
                    }

                    .container {
                        max-width: 1200px;
                        margin: 20px auto;
                        padding: 0 20px;
                    }

                    .menu-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                        gap: 20px;
                        margin-top: 20px;
                    }

                    .menu-item {
                        background-color: white;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                        text-align: center;
                        transition: transform 0.2s;
                    }

                    .menu-item:hover {
                        transform: translateY(-5px);
                        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
                    }

                    .menu-item h3 {
                        margin: 0 0 10px 0;
                        color: #333;
                    }

                    .menu-item p {
                        color: #666;
                        margin: 0 0 15px 0;
                    }

                    .menu-btn {
                        background-color: #4CAF50;
                        color: white;
                        padding: 10px 20px;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                        text-decoration: none;
                        display: inline-block;
                    }

                    .menu-btn:hover {
                        background-color: #45a049;
                    }

                    .stats-grid {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                        gap: 15px;
                        margin-bottom: 30px;
                    }

                    .stat-card {
                        background-color: white;
                        padding: 20px;
                        border-radius: 8px;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                        text-align: center;
                    }

                    .stat-card h3 {
                        margin: 0 0 10px 0;
                        color: #333;
                        font-size: 14px;
                    }

                    .stat-card .number {
                        font-size: 24px;
                        font-weight: bold;
                        color: #4CAF50;
                    }

                    .hidden {
                        display: none;
                    }
                </style>
            </head>

            <body>
                <div class="header">
                    <h1>超市销售管理系统</h1>
                    <div class="user-info">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <span>欢迎，${sessionScope.user.username} (${sessionScope.user.role})</span>
                                <a href="login.jsp" class="logout-btn">退出登录</a>
                            </c:when>
                            <c:otherwise>
                                <span>请先<a href="login.jsp" style="color:#fff;text-decoration:underline;">登录</a></span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="container">
                    <div class="stats-grid">
                        <div class="stat-card">
                            <h3>今日销售额</h3>
                            <div class="number">¥${stats.todaySales}</div>
                        </div>
                        <div class="stat-card">
                            <h3>今日订单数</h3>
                            <div class="number">${stats.todayOrders}</div>
                        </div>
                        <div class="stat-card">
                            <h3>商品总数</h3>
                            <div class="number">${stats.totalProducts}</div>
                        </div>
                        <div class="stat-card">
                            <h3>会员总数</h3>
                            <div class="number">${stats.totalMembers}</div>
                        </div>
                    </div>

                    <div class="menu-grid">
                        <!-- 收银台：仅admin和cashier可见 -->
                        <c:if
                            test="${not empty sessionScope.user && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'cashier')}">
                            <div class="menu-item">
                                <h3>收银台</h3>
                                <p>进行商品销售、结算、打印小票等操作</p>
                                <a href="cashier" class="menu-btn">进入收银台</a>
                            </div>
                        </c:if>

                        <!-- 商品管理：仅admin和product可见 -->
                        <c:if
                            test="${not empty sessionScope.user && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'product')}">
                            <div class="menu-item">
                                <h3>商品管理</h3>
                                <p>商品信息管理、库存管理、货架管理等</p>
                                <a href="product/" class="menu-btn">商品管理</a>
                            </div>
                        </c:if>

                        <!-- 会员管理：仅admin和member可见 -->
                        <c:if
                            test="${not empty sessionScope.user && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'member')}">
                            <div class="menu-item">
                                <h3>会员管理</h3>
                                <p>会员信息管理、积分管理、等级管理等</p>
                                <a href="member/" class="menu-btn">会员管理</a>
                            </div>
                        </c:if>

                        <!-- 报表统计：仅admin和product可见 -->
                        <c:if
                            test="${not empty sessionScope.user && (sessionScope.user.role == 'admin' || sessionScope.user.role == 'product')}">
                            <div class="menu-item">
                                <h3>报表统计</h3>
                                <p>销售报表、商品报表、会员报表等</p>
                                <a href="report/" class="menu-btn">查看报表</a>
                            </div>
                        </c:if>

                        <!-- 用户管理：仅admin可见 -->
                        <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'admin'}">
                            <div class="menu-item">
                                <h3>用户管理</h3>
                                <p>系统用户管理、权限管理等</p>
                                <a href="user/" class="menu-btn">用户管理</a>
                            </div>
                        </c:if>
                    </div>
                </div>

                <script>
                    // 检查用户权限，隐藏无权限的菜单项
                    function checkPermissions() {
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                const userRole = '${sessionScope.user.role}';
                            </c:when>
                            <c:otherwise>
                                const userRole = '';
                            </c:otherwise>
                        </c:choose>
                        const menuItems = document.querySelectorAll('.menu-item');
                        menuItems.forEach(item => {
                            const btn = item.querySelector('.menu-btn');
                            if (btn) {
                                const href = btn.getAttribute('href');
                                if (href.includes('product') && userRole !== 'admin' && userRole !== 'product') {
                                    item.classList.add('hidden');
                                } else if (href.includes('member') && userRole !== 'admin' && userRole !== 'member') {
                                    item.classList.add('hidden');
                                } else if (href.includes('report') && userRole !== 'admin' && userRole !== 'product') {
                                    item.classList.add('hidden');
                                } else if (href.includes('user') && userRole !== 'admin') {
                                    item.classList.add('hidden');
                                }
                            }
                        });
                    }

                    // 页面加载时检查权限
                    document.addEventListener('DOMContentLoaded', checkPermissions);
                </script>
            </body>

            </html>