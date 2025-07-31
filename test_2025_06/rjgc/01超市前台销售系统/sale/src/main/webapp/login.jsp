<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>收银员登录系统</title>
        <style>
            body {
                font-family: 'Segoe UI', Arial, sans-serif;
                background: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .login-container {
                background: #fff;
                padding: 40px 30px 30px 30px;
                border-radius: 12px;
                box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.2);
                min-width: 350px;
            }

            .login-container h2 {
                text-align: center;
                margin-bottom: 25px;
                color: #333;
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
                transition: border 0.2s;
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

            .error {
                color: #e53935;
                background: #ffebee;
                border: 1px solid #ffcdd2;
                border-radius: 4px;
                padding: 8px 12px;
                margin-bottom: 15px;
                text-align: center;
            }
        </style>
    </head>

    <body>
        <div class="login-container">
            <h2>超市前台销售系统登录</h2>
            <% if(request.getAttribute("error") !=null) { %>
                <div class="error">${error}</div>
                <% } %>
                    <form action="login" method="post">
                        <div class="form-group">
                            <label for="username">用户名：</label>
                            <input type="text" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">密码：</label>
                            <input type="password" id="password" name="password" required>
                        </div>
                        <div class="form-group">
                            <label for="role">身份：</label>
                            <select id="role" name="role" required>
                                <option value="cashier">收银员</option>
                                <option value="admin">系统管理员</option>
                                <option value="product">商品管理员</option>
                                <option value="member">会员</option>
                            </select>
                        </div>
                        <button type="submit" class="btn">登录</button>
                    </form>
        </div>
    </body>

    </html>