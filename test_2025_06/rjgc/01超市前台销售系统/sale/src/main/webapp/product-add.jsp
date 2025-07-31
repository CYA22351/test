<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>添加商品</title>
            <style>
                body {
                    font-family: 'Segoe UI', Arial, sans-serif;
                    background: #f4f4f4;
                }

                .container {
                    max-width: 500px;
                    margin: 40px auto;
                    background: #fff;
                    border-radius: 10px;
                    box-shadow: 0 2px 10px #ccc;
                    padding: 30px;
                }

                h2 {
                    text-align: center;
                    color: #333;
                    margin-bottom: 25px;
                }

                .form-group {
                    margin-bottom: 18px;
                }

                .form-group label {
                    display: block;
                    margin-bottom: 6px;
                    color: #555;
                    font-weight: 500;
                }

                .form-group input,
                .form-group select {
                    width: 100%;
                    padding: 10px;
                    border: 1px solid #bdbdbd;
                    border-radius: 6px;
                    font-size: 15px;
                    background: #f9f9f9;
                }

                .form-group input:focus,
                .form-group select:focus {
                    border: 1.5px solid #4CAF50;
                    outline: none;
                }

                .btn {
                    background: linear-gradient(90deg, #43e97b 0%, #38f9d7 100%);
                    color: white;
                    padding: 12px 0;
                    border: none;
                    border-radius: 6px;
                    cursor: pointer;
                    width: 100%;
                    font-size: 16px;
                    font-weight: bold;
                    letter-spacing: 1px;
                    box-shadow: 0 2px 8px rgba(67, 233, 123, 0.15);
                    transition: background 0.2s;
                }

                .btn:hover {
                    background: linear-gradient(90deg, #38f9d7 0%, #43e97b 100%);
                }
            </style>
        </head>

        <body>
            <div class="container">
                <h2>添加商品</h2>
                <form action="/sale_system_war_exploded/product" method="post" accept-charset="UTF-8">
                    <div class="form-group">
                        <label for="name">商品名称：</label>
                        <input type="text" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="barcode">商品条码：</label>
                        <input type="text" id="barcode" name="barcode" required>
                    </div>
                    <div class="form-group">
                        <label for="categoryId">商品分类：</label>
                        <select id="categoryId" name="categoryId" required>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.id}">${cat.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="price">售价：</label>
                        <input type="number" step="0.01" id="price" name="price" required>
                    </div>
                    <div class="form-group">
                        <label for="costPrice">成本价：</label>
                        <input type="number" step="0.01" id="costPrice" name="costPrice">
                    </div>
                    <div class="form-group">
                        <label for="shelfCode">货架编码：</label>
                        <input type="text" id="shelfCode" name="shelfCode">
                    </div>
                    <div class="form-group">
                        <label for="stock">库存数量：</label>
                        <input type="number" id="stock" name="stock" value="0" required>
                    </div>
                    <div class="form-group">
                        <label for="minStock">最低库存警戒值：</label>
                        <input type="number" id="minStock" name="minStock" value="10">
                    </div>
                    <button type="submit" class="btn">提交</button>
                </form>
            </div>
        </body>

        </html>