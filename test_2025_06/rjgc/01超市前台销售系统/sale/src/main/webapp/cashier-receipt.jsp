<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <% entity.Sale sale=(entity.Sale) request.getAttribute("sale"); String receipt=(String)
            request.getAttribute("receipt"); %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>销售小票</title>
                <style>
                    body {
                        font-family: monospace, Arial, sans-serif;
                        background: #fff;
                        margin: 0;
                        padding: 0;
                    }

                    .receipt-box {
                        max-width: 400px;
                        margin: 30px auto;
                        padding: 20px;
                        border: 1px solid #eee;
                        box-shadow: 0 0 10px #ccc;
                        background: #fff;
                    }

                    .receipt-title {
                        text-align: center;
                        font-size: 20px;
                        font-weight: bold;
                        margin-bottom: 10px;
                    }

                    .receipt-info {
                        font-size: 14px;
                        margin-bottom: 10px;
                    }

                    .receipt-table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-bottom: 10px;
                    }

                    .receipt-table th,
                    .receipt-table td {
                        border-bottom: 1px dashed #ccc;
                        padding: 4px 0;
                        font-size: 14px;
                    }

                    .receipt-table th {
                        text-align: left;
                    }

                    .total-row {
                        font-weight: bold;
                    }

                    .footer {
                        text-align: center;
                        margin-top: 15px;
                        font-size: 14px;
                        color: #888;
                    }

                    .print-btn {
                        display: block;
                        width: 100%;
                        margin: 15px 0 0 0;
                        padding: 10px;
                        background: #2196F3;
                        color: #fff;
                        border: none;
                        border-radius: 4px;
                        font-size: 16px;
                        cursor: pointer;
                    }
                </style>
            </head>

            <body>
                <div class="receipt-box">
                    <div class="receipt-title">销售小票</div>
                    <div class="receipt-info">
                        单号：<%= sale !=null ? sale.getSaleNo() : "" %><br />
                            收银员：<%= sale !=null ? sale.getCashierName() : "" %><br />
                                时间：<%= sale !=null && sale.getCreateTime() !=null ? new
                                    java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sale.getCreateTime()) : ""
                                    %><br />
                                    会员：<%= sale !=null && sale.getMemberName() !=null ? sale.getMemberName() : "非会员" %>
                    </div>
                    <table class="receipt-table">
                        <thead>
                            <tr>
                                <th>商品</th>
                                <th>数量</th>
                                <th>单价</th>
                                <th>小计</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${sale.items}">
                                <tr>
                                    <td>${item.productName}</td>
                                    <td>${item.quantity}</td>
                                    <td>¥${item.price}</td>
                                    <td>¥${item.amount}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="receipt-info">
                        应收总额：<b>¥<%= sale !=null && sale.getTotalAmount() !=null ? String.format("%.2f",
                                sale.getTotalAmount()) : "0.00" %></b><br />
                        优惠金额：<b>¥<%= sale !=null && sale.getDiscountAmount() !=null ? String.format("%.2f",
                                sale.getDiscountAmount()) : "0.00" %></b><br />
                        实收金额：<b>¥<%= sale !=null && sale.getActualAmount() !=null ? String.format("%.2f",
                                sale.getActualAmount()) : "0.00" %></b><br />
                        支付方式：<%= sale !=null ? sale.getPayType() : "" %>
                    </div>
                    <div class="footer">谢谢惠顾！</div>
                    <button class="print-btn" onclick="window.print()">打印</button>
                </div>
            </body>

            </html>