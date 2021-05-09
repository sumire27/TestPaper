<%@ page import="org.springframework.web.context.request.ServletRequestAttributes" %>
<%@ page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${ctx}/css/stuMa.css" />
    <link rel="stylesheet" href="${ctx}/css/scoreMa.css" />
    <title>成绩管理</title>
</head>
<body>
<div class="addUser">
    <a href="${ctx}/score/toScoreMa" class="add">返回</a>
</div>


<form class="stuForm">
    <table class="stuTable">
        <tr>
            <th></th>
            <th class="name">试卷编号</th>
            <th class="name">试卷名称</th>
            <th class="process">考生学号</th>
            <th class="node">考生姓名</th>
            <th class="time">得分</th>
            <th class="operate">操作</th>
        </tr>

        <c:forEach  var="paper" items="${paperList}" varStatus="loop">
            <tr>
                <td>${paper.paperId}</td>
                <td>${paper.paperName}</td>
                <td>
                        ${paper.userId}
                </td>
                <td>
                        ${userName[loop.count-1]}

                </td>
                <td>${paper.score}</td>
                <td>
                    <a id="deleteStu" href="javascript:;" onclick="deleteYes('${ctx}/score/deleteScore?paperId=${paper.paperId}')">删除</a>

                </td>
            </tr>
            <tr><td colspan="7" id="line">  </td></tr>

        </c:forEach>
    </table>
</form>
<script type="text/javascript" src="${ctx}/js/jquery-2.0.3.js"></script>
<script type="text/javascript">
    function deleteYes(url) {
        var r=confirm("你确定要删除吗");
        if (r==true)
            window.location.href=url;
        else
            return;

    }

    function search(url) {
        var searchInfo=$('.searchInfo').val();
        if (searchInfo==null)
            alert("输入为空!");
        else
            window.location.href=url+'?searchInfo='+searchInfo;
    }


</script>


</body>
</html>