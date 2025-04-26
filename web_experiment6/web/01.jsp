<%@ page import="java.util.HashMap" %>
<%@ page import="com.hkd.entity.Signon" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
<head>
    <title></title>
</head>
<body>
<%
    String uname[]={"tom","jack"};
    request.setAttribute("uname",uname);
    HashMap<String,String> map=new HashMap<String,String>();
    map.put("uname", "tom");
    map.put("pwd","1234");
    request.setAttribute("map", map);
    Signon signon=new Signon();
    signon.setUsername("rose");
    signon.setPassword("1234");
    session.setAttribute("user", signon);
%>
<!-- EL可以实现数据的获取及显示 ，但是不能实现数据的逻辑处理-->
<!-- 1.利用el表达式访问数组 -->
${requestScope.uname[0] }
${uname[1] }
<!-- 2.利用EL表达式获取集合中的数据 -->
${requestScope.map["uname"] }
<!-- 3.利用el表达式访问对象及对象的属性 -->
${sessionScope.user.username }
<!-- 4.利用el表达式可以调用有返回值的函数 -->
${requestScope.map.get("uname") }
${sessionScope.user.getUsername() }
${sessionScope.user.showInfo() }
</body>
</html>