<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>销售报表（筛选结果）</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background: #f4f4f4;
                    }

                    .container {
                        width: 90%;
                        margin: 30px auto;
                        background: #fff;
                        padding: 20px;
                        border-radius: 8px;
                    }

                    h2 {
                        margin-top: 0;
                    }

                    .actions {
                        margin-bottom: 15px;
                    }

                    .actions button {
                        margin-right: 10px;
                    }

                    table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-top: 10px;
                    }

                    th,
                    td {
                        border: 1px solid #ddd;
                        padding: 8px;
                    }

                    th {
                        background: #f2f2f2;
                    }

                    .search-box {
                        float: right;
                    }
                </style>
            </head>

            <body>
                <div class="container">
                    <h2>销售报表（筛选结果）</h2>
                    <div class="actions">
                        <form class="search-box" action="report/sales" method="get" style="display:inline;">
                            <label>日期范围：</label>
                            <input type="date" name="startDate" value="${param.startDate}">
                            <span>至</span>
                            <input type="date" name="endDate" value="${param.endDate}">
                            <button type="submit">查询</button>
                        </form>
                    </div>
                    <c:if test="${not empty message}">
                        <div class="message success">${message}</div>
                    </c:if>
                    <c:if test="${not empty error}">
                        <div class="message error">${error}</div>
                    </c:if>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>销售单号</th>
                                <th>收银员</th>
                                <th>会员</th>
                                <th>总额</th>
                                <th>优惠</th>
                                <th>实收</th>
                                <th>支付方式</th>
                                <th>状态</th>
                                <th>时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty sales}">
                                <tr>
                                    <td colspan="9" style="text-align:center;">暂无销售数据</td>
                                </tr>
                            </c:if>
                            <c:forEach var="sale" items="${sales}">
                                <tr>
                                    <td>${sale.id}</td>
                                    <td>${sale.saleNo}</td>
                                    <td>${sale.cashierName}</td>
                                    <td>${sale.memberName}</td>
                                    <td>¥${sale.totalAmount}</td>
                                    <td>¥${sale.discountAmount}</td>
                                    <td>¥${sale.actualAmount}</td>
                                    <td>${sale.payType}</td>
                                    <td>${sale.status}</td>
                                    <td>
                                        <fmt:formatDate value="${sale.createTime}" pattern="yyyy-MM-dd HH:mm:ss"
                                            timeZone="Asia/Shanghai" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>