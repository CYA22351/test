<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>已知a=3  b=4 交换两个变量 EL+JSTL</title>
</head>
<body>

交换前: a=${3},b=${4} <br/>

<c:set var="a" value="3" /><br/>
<c:set var="b" value="4" /><br/>

<c:set var="t" value="${a}" /><br/>
<c:set var="a" value="${b}" /><br/>
<c:set var="b" value="${t}" /><br/>

交换后: a=${a},b=${b} <br/>

</body>
</html>