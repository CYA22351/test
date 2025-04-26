<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
姓名:${param.name}<br/>
密码:${param.pass}<br/>
性别:${param.sex}<br/>
性格:${paramValues.xingge[0] }<br/>
简介:${param.jianjie}<br/>
城市:${param.city}
</body>
</html>