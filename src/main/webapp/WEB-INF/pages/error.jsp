<%--
  Created by IntelliJ IDEA.
  User: 15679
  Date: 2021/5/8
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>出错</title>
</head>
<body>
<h3>无人参加此次考试</h3>

<a href="${ctx}/score/toScoreMa">返回</a>
</body>
</html>
