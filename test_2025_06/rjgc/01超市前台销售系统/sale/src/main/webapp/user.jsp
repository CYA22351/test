<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>用户管理</title>
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
                <h2>用户管理</h2>
                <div class="actions">
                    <button class="add-btn" onclick="location.href='/sale_system_war_exploded/user/add'">添加用户</button>
                    <form class="search-box" action="user" method="get" style="display:inline;">
                        <input type="text" name="keyword" placeholder="用户名" value="${param.keyword}">
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
                            <th>用户名</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.role}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.status == 1}">启用</c:when>
                                        <c:otherwise>停用</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <button class="edit-btn"
                                        onclick="location.href='/sale_system_war_exploded/user/edit?id=${user.id}'">编辑</button>
                                    <button class="delete-btn"
                                        onclick="if(confirm('确定删除？')) location.href='/sale_system_war_exploded/user/delete?id=${user.id}'">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </body>

        </html>