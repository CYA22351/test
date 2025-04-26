<%--
  Created by IntelliJ IDEA.
  User: 陈奕安
  Date: 2025/4/15
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:if test="${flag}">
    <h2 >用户名或密码错误，请重新输入</h2>
</c:if>
<form action="doLogin.jsp" method="post" >
    用户名：<input type="text" name="uname"> <br>
    密码：<input type="text" name="pwd"> <br>
    <input type="submit" name="btn" value="提交"> <br>
    <input type="reset" name="btn" value="重置"> <br>
</form>
</body>
</html>