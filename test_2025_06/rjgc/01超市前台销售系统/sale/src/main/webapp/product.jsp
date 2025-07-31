<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>商品管理</title>
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

                .edit-btn,
                .delete-btn {
                    padding: 5px 10px;
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                }

                .edit-btn {
                    background: #4CAF50;
                    color: #fff;
                }

                .delete-btn {
                    background: #f44336;
                    color: #fff;
                }

                .add-btn {
                    background: #2196F3;
                    color: #fff;
                }

                .search-box {
                    float: right;
                }
            </style>
        </head>

        <body>
            <div class="container">
                <h2>商品管理</h2>
                <div class="actions">
                    <button class="add-btn"
                        onclick="location.href='/sale_system_war_exploded/product/add'">添加商品</button>
                    <form class="search-box" action="product" method="get" style="display:inline;">
                        <input type="text" name="keyword" placeholder="商品名称/条码" value="${param.keyword}">
                        <button type="submit">搜索</button>
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
                            <th>编号</th>
                            <th>名称</th>
                            <th>条码</th>
                            <th>分类</th>
                            <th>价格</th>
                            <th>库存</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.barcode}</td>
                                <td>${product.categoryName}</td>
                                <td>¥${product.price}</td>
                                <td>${product.stock}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${product.status == 1}">上架</c:when>
                                        <c:otherwise>下架</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button class="edit-btn"
                                        onclick="location.href='/sale_system_war_exploded/product/edit?id=${product.id}'">编辑</button>
                                    <button class="delete-btn"
                                        onclick="if(confirm('确定删除？')) location.href='product/delete?id=${product.id}'">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>