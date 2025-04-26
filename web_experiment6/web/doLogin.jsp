<%--
  Created by IntelliJ IDEA.
  User: 陈奕安
  Date: 2025/4/15
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    boolean flag=false;
%>
<c:set var="uname" value="${param.uname}" scope="session" />
<c:set var="pwd" value="${param.pwd}" scope="session" />
<c:if test="${uname.equals('tom')&&pwd.equals('1234')}" >
    <%
        response.sendRedirect("welcome.jsp");
    %>
</c:if>
<c:if test="${!uname.equals('tom')||!pwd.equals('1234')}">


    <%
        flag=true;
        session.setAttribute("flag",flag);
        response.sendRedirect("login.jsp");

    %>
</c:if>
</body>
</html>

