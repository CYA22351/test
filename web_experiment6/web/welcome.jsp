<%--
  Created by IntelliJ IDEA.
  User: 陈奕安
  Date: 2025/4/15
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
欢迎<c:out value="${sessionScope.uname}" />登录系统
</body>
</html>
