<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <% entity.User currentUser=(entity.User) session.getAttribute("user"); if (currentUser==null ||
            !("cashier".equals(currentUser.getRole()) || "admin" .equals(currentUser.getRole()))) {
            response.sendRedirect("login.jsp?error=unauthorized"); return; } %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>收银台</title>
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

                    .back-btn {
                        background-color: #666;
                        color: white;
                        padding: 8px 15px;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                        text-decoration: none;
                    }

                    .container {
                        display: flex;
                        height: calc(100vh - 80px);
                    }

                    .left-panel {
                        width: 70%;
                        padding: 20px;
                        background-color: white;
                        margin: 20px;
                        border-radius: 8px;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                    }

                    .right-panel {
                        width: 30%;
                        padding: 20px;
                        background-color: white;
                        margin: 20px 20px 20px 0;
                        border-radius: 8px;
                        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                    }

                    .product-input {
                        display: flex;
                        gap: 10px;
                        margin-bottom: 20px;
                    }

                    .product-input input {
                        flex: 1;
                        padding: 10px;
                        border: 1px solid #ddd;
                        border-radius: 4px;
                    }

                    .product-input button {
                        background-color: #4CAF50;
                        color: white;
                        padding: 10px 20px;
                        border: none;
                        border-radius: 4px;
                        cursor: pointer;
                    }

                    .cart-table {
                        width: 100%;
                        border-collapse: collapse;
                        margin-bottom: 20px;
                    }

                    .cart-table th,
                    .cart-table td {
                        border: 1px solid #ddd;
                        padding: 10px;
                        text-align: left;
                    }

                    .cart-table th {
                        background-color: #f2f2f2;
                    }

                    .quantity-control {
                        display: flex;
                        align-items: center;
                        gap: 5px;
                    }

                    .quantity-btn {
                        background-color: #ddd;
                        border: none;
                        padding: 5px 10px;
                        cursor: pointer;
                        border-radius: 3px;
                    }

                    .remove-btn {
                        background-color: #f44336;
                        color: white;
                        border: none;
                        padding: 5px 10px;
                        cursor: pointer;
                        border-radius: 3px;
                    }

                    .payment-section {
                        margin-top: 20px;
                        padding: 20px;
                        background-color: #f9f9f9;
                        border-radius: 8px;
                    }

                    .total-info {
                        display: flex;
                        justify-content: space-between;
                        margin-bottom: 10px;
                        font-size: 16px;
                    }

                    .total-amount {
                        font-size: 24px;
                        font-weight: bold;
                        color: #4CAF50;
                        text-align: right;
                        margin-bottom: 20px;
                    }

                    .payment-methods {
                        display: grid;
                        grid-template-columns: 1fr 1fr;
                        gap: 10px;
                        margin-bottom: 20px;
                    }

                    .payment-btn {
                        padding: 15px;
                        border: 2px solid #ddd;
                        background-color: white;
                        cursor: pointer;
                        border-radius: 8px;
                        text-align: center;
                        transition: all 0.3s;
                    }

                    .payment-btn:hover {
                        border-color: #4CAF50;
                        background-color: #f0f8f0;
                    }

                    .payment-btn.selected {
                        border-color: #4CAF50;
                        background-color: #4CAF50;
                        color: white;
                    }

                    .action-buttons {
                        display: grid;
                        grid-template-columns: 1fr 1fr;
                        gap: 10px;
                    }

                    .action-btn {
                        padding: 15px;
                        border: none;
                        border-radius: 8px;
                        cursor: pointer;
                        font-size: 16px;
                        font-weight: bold;
                    }

                    .checkout-btn {
                        background-color: #4CAF50;
                        color: white;
                    }

                    .cancel-btn {
                        background-color: #f44336;
                        color: white;
                    }

                    .clear-btn {
                        background-color: #2196F3;
                        color: white;
                    }

                    .member-section {
                        margin-bottom: 20px;
                        padding: 15px;
                        background-color: #f0f8f0;
                        border-radius: 8px;
                    }

                    .member-info {
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        margin-bottom: 10px;
                    }

                    .member-name {
                        font-weight: bold;
                        color: #4CAF50;
                    }

                    .member-points {
                        color: #666;
                    }

                    .discount-info {
                        color: #4CAF50;
                        font-weight: bold;
                    }

                    .message {
                        padding: 10px;
                        margin-bottom: 10px;
                        border-radius: 4px;
                    }

                    .message.success {
                        background-color: #d4edda;
                        color: #155724;
                        border: 1px solid #c3e6cb;
                    }

                    .message.error {
                        background-color: #f8d7da;
                        color: #721c24;
                        border: 1px solid #f5c6cb;
                    }
                </style>
            </head>

            <body>
                <div class="header">
                    <h1>收银台</h1>
                    <a href="main.jsp" class="back-btn">返回主页</a>
                </div>
                <div class="container">
                    <div class="left-panel">
                        <c:if test="${not empty message}">
                            <div class="message ${messageType}">${message}</div>
                        </c:if>
                        <c:if test="${not empty error}">
                            <div class="message error">${error}</div>
                            <script>document.addEventListener('DOMContentLoaded', function () { alert('${error}'); });</script>
                        </c:if>
                        <div class="product-input">
                            <input type="text" id="barcode" placeholder="扫描商品条码或输入商品名称" onkeypress="handleEnter(event)">
                            <button onclick="addProduct()">添加商品</button>
                        </div>
                        <table class="cart-table">
                            <thead>
                                <tr>
                                    <th>商品名称</th>
                                    <th>单价</th>
                                    <th>数量</th>
                                    <th>小计</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="cartBody">
                                <c:forEach var="item" items="${cartItems}">
                                    <tr data-product-id="${item.productId}">
                                        <td>${item.productName}</td>
                                        <td>¥${item.price}</td>
                                        <td>
                                            <div class="quantity-control">
                                                <button class="quantity-btn"
                                                    onclick="setQuantity(${item.productId}, ${item.quantity - 1})" <c:if
                                                    test="${item.quantity == 0}">disabled</c:if>>-</button>
                                                <span>${item.quantity}</span>
                                                <button class="quantity-btn"
                                                    onclick="setQuantity(${item.productId}, ${item.quantity + 1})">+</button>
                                            </div>
                                        </td>
                                        <td>¥${item.amount}</td>
                                        <td>
                                            <button class="remove-btn" onclick="setQuantity(${item.productId}, 0)" <c:if
                                                test="${item.quantity == 0}">disabled</c:if>>清零</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="payment-section">
                            <div class="total-info">
                                <span>商品总额：</span>
                                <span>¥${subtotal}</span>
                            </div>
                            <div class="total-info">
                                <span>会员折扣：</span>
                                <span>-¥${discount}</span>
                            </div>
                            <div class="total-amount">
                                应付金额：¥${totalAmount}
                            </div>
                            <div class="payment-methods">
                                <div class="payment-btn" onclick="selectPayment('cash')">现金支付</div>
                                <div class="payment-btn" onclick="selectPayment('card')">刷卡支付</div>
                                <div class="payment-btn" onclick="selectPayment('wechat')">微信支付</div>
                                <div class="payment-btn" onclick="selectPayment('alipay')">支付宝</div>
                            </div>
                            <div class="action-buttons">
                                <button class="action-btn checkout-btn" onclick="checkout()">结算</button>
                                <button class="action-btn cancel-btn" onclick="cancelOrder()">取消</button>
                                <button class="action-btn clear-btn" onclick="clearCart()">清空</button>
                            </div>
                        </div>
                    </div>
                    <div class="right-panel">
                        <div class="member-section">
                            <h3>会员信息</h3>
                            <div class="product-input">
                                <input type="text" id="memberCard" placeholder="输入会员卡号"
                                    onkeypress="handleMemberEnter(event)">
                                <button onclick="searchMember()">查询</button>
                                <button onclick="setMember()">设为本单会员</button>
                            </div>
                            <c:if test="${not empty currentMember}">
                                <div class="member-info">
                                    <span class="member-name">${currentMember.name}</span>
                                    <span class="member-points">积分：${currentMember.points}</span>
                                </div>
                                <div class="discount-info">
                                    会员等级：${currentMember.level} (${memberDiscount}折)
                                </div>
                            </c:if>
                        </div>
                        <div>
                            <h3>快捷操作</h3>
                            <button onclick="printReceipt()"
                                style="width: 100%; margin-bottom: 10px; padding: 10px; background-color: #2196F3; color: white; border: none; border-radius: 4px; cursor: pointer;">打印小票</button>
                            <button onclick="showProductList()"
                                style="width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer;">商品列表</button>
                        </div>
                    </div>
                </div>
                <c:set var="totalAmountValue" value="${empty totalAmount ? 0 : totalAmount}" />
                <script>
                    let selectedPayment = '';
                    // 通过URL参数获取saleId
                    function getSaleId() {
                        const urlParams = new URLSearchParams(window.location.search);
                        return urlParams.get('saleId');
                    }
                    // 页面加载时如无saleId参数只跳一次new
                    document.addEventListener('DOMContentLoaded', function () {
                        if (!getSaleId()) {
                            window.location.replace('./new');
                            return;
                        }
                        if (document.getElementById('barcode')) {
                            document.getElementById('barcode').focus();
                        }
                    });
                    function handleEnter(event) {
                        if (event.key === 'Enter') {
                            addProduct();
                        }
                    }
                    function handleMemberEnter(event) {
                        if (event.key === 'Enter') {
                            searchMember();
                        }
                    }
                    function addProduct() {
                        const barcode = document.getElementById('barcode').value.trim();
                        const saleId = getSaleId();
                        if (!barcode) {
                            alert('请输入商品条码或名称');
                            return;
                        }
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        var bodyStr = 'saleId=' + saleId + '&barcode=' + encodeURIComponent(barcode) + '&quantity=1';
                        fetch('cashier/', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                            body: bodyStr
                        })
                            .then(function (response) {
                                if (response.redirected) {
                                    window.location.href = response.url;
                                    return null;
                                }
                                return response.text();
                            })
                            .then(function (data) {
                                location.reload();
                            })
                            .catch(function (error) {
                                alert('添加商品失败');
                            });
                        document.getElementById('barcode').value = '';
                    }
                    function setQuantity(productId, newQty) {
                        const saleId = getSaleId();
                        if (newQty < 0) newQty = 0;
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        fetch('cashier/updateQuantity', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                            body: 'saleId=' + saleId + '&productId=' + productId + '&change=' + (newQty - getCurrentQty(productId))
                        })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    location.reload();
                                } else {
                                    alert(data.message || '更新数量失败');
                                }
                            })
                            .catch(error => {
                                alert('更新数量失败');
                            });
                    }
                    function getCurrentQty(productId) {
                        var row = document.querySelector('tr[data-product-id="' + productId + '"]');
                        if (row) {
                            var span = row.querySelector('.quantity-control span');
                            if (span) return parseInt(span.textContent);
                        }
                        return 0;
                    }
                    function removeItem(productId) {
                        const saleId = getSaleId();
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        if (confirm('确定要删除这个商品吗？')) {
                            fetch('cashier/removeItem', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                                body: 'saleId=' + saleId + '&productId=' + productId
                            })
                                .then(response => response.json())
                                .then(data => {
                                    if (data.success) {
                                        location.reload();
                                    } else {
                                        alert(data.message || '删除商品失败');
                                    }
                                })
                                .catch(error => {
                                    alert('删除商品失败');
                                });
                        }
                    }
                    function selectPayment(method) {
                        selectedPayment = method;
                        document.querySelectorAll('.payment-btn').forEach(btn => {
                            btn.classList.remove('selected');
                        });
                        event.target.classList.add('selected');
                    }
                    function checkout() {
                        const saleId = getSaleId();
                        if (!selectedPayment) {
                            alert('请选择支付方式');
                            return;
                        }
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        const paymentAmount = prompt('请输入实收金额：');
                        if (paymentAmount === null) return;
                        const totalAmount = ${ totalAmountValue };
                        if (isNaN(paymentAmount) || parseFloat(paymentAmount) < totalAmount) {
                            alert('实收金额不能小于应付金额');
                            return;
                        }
                        fetch('cashier/checkout', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                            body: 'saleId=' + saleId + '&paymentAmount=' + paymentAmount + '&paymentMethod=' + selectedPayment
                        })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    alert('结算成功！');
                                    window.location.href = 'cashier';
                                } else {
                                    alert(data.message || '结算失败');
                                }
                            })
                            .catch(error => {
                                alert('结算失败');
                            });
                    }
                    function cancelOrder() {
                        const saleId = getSaleId();
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        if (confirm('确定要取消订单吗？')) {
                            fetch('cashier/cancel', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                                body: 'saleId=' + saleId
                            })
                                .then(response => response.json())
                                .then(data => {
                                    if (data.success) {
                                        alert('订单已取消');
                                        window.location.href = 'cashier';
                                    } else {
                                        alert(data.message || '取消订单失败');
                                    }
                                })
                                .catch(error => {
                                    alert('取消订单失败');
                                });
                        }
                    }
                    function clearCart() {
                        const saleId = getSaleId();
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        if (confirm('确定要清空购物车吗？')) {
                            fetch('cashier/clear', {
                                method: 'POST',
                                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                                body: 'saleId=' + saleId
                            })
                                .then(response => response.json())
                                .then(data => {
                                    if (data.success) {
                                        location.reload();
                                    } else {
                                        alert(data.message || '清空购物车失败');
                                    }
                                })
                                .catch(error => {
                                    alert('清空购物车失败');
                                });
                        }
                    }
                    function searchMember() {
                        const cardNo = document.getElementById('memberCard').value.trim();
                        if (!cardNo) {
                            alert('请输入会员卡号');
                            return;
                        }
                        fetch('cashier/searchMember?cardNo=' + encodeURIComponent(cardNo))
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    alert('会员：' + data.name + '，等级：' + data.level + '，积分：' + data.points);
                                } else {
                                    alert(data.message || '会员不存在');
                                }
                            })
                            .catch(error => {
                                alert('查询会员失败');
                            });
                    }
                    function setMember() {
                        const cardNo = document.getElementById('memberCard').value.trim();
                        const saleId = getSaleId();
                        if (!cardNo) {
                            alert('请输入会员卡号');
                            return;
                        }
                        if (!saleId || saleId == '0') {
                            alert('单据ID无效，请刷新页面或从收银台入口重新进入');
                            return;
                        }
                        fetch('cashier/setMember', {
                            method: 'POST',
                            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                            body: 'saleId=' + saleId + '&cardNo=' + encodeURIComponent(cardNo)
                        })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    location.reload();
                                } else {
                                    alert(data.message || '设置会员失败');
                                }
                            })
                            .catch(error => {
                                alert('设置会员失败');
                            });
                    }
                    function printReceipt() {
                        const saleId = getSaleId();
                        if (saleId) {
                            window.open('cashier/print?saleId=' + saleId, '_blank');
                        } else {
                            alert('没有可打印的订单');
                        }
                    }
                    function showProductList() {
                        window.location.href = 'product/';
                    }
                </script>
            </body>

            </html>