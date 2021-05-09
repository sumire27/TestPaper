<%--
  Created by IntelliJ IDEA.
  User: 15679
  Date: 2021/3/2
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>教师登录</title>
    <link rel="stylesheet" href="${ctx}/css/tLogin.css" />
</head>
<body>
<div class="loginBox">
    <div class="title"><h2>教师登录</h2></div>
    <form action="${ctx}/user/userLogin" method="post">
        <div class="username" >
            <span>用户名</span><br/>
            <input type="text" name="userId"/>
        </div>
        <div class="password">
            <span>密码</span><br>
            <input type="password" name="userPwd"/>
        </div>
        <input type="submit" value="登录" class="logBtn"/>
        <div style="color: red" id="tipInfo" align="justify">${message }</div>

    </form>
    <div class="bottom">
        <ul>
            <li>&copy;Sumire</li>
            <span>|</span>
            <li>使用条款</li>
            <span>|</span>
            <li>隐私政策</li>
        </ul>
    </div>
</div>
</body>
</html>
