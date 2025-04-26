<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>注册表单</title>
</head>
<body>
<form method="post" action="doRegister.jsp">
    姓名: <input type="text" name="name" /> <p>
    密码: <input type="password" name="pass" /> <p>
    性别: <input type="radio" name="sex" value="男人" checked="checked" />男
    <input type="radio" name="sex" value="女人" />女 <p>
    性格: <input type="checkbox" name="xingge" checked="checked" value="热情大方" />热情大方
    <input type="checkbox" name="xingge" value="温柔体贴" />温柔体贴
    <input type="checkbox" name="xingge" value="多愁善感" />多愁善感 <p>
    简介: <textarea name="jianjie"></textarea> <p>
    城市: <select name="city">
    <option value="北京">北京</option>
    <option value="上海">上海</option>
</select> <p>
    <input type="submit" name="Submit" value="提交">
</form>
</body>
</html>