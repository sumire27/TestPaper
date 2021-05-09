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
<h3 class="paperName">${paperName}</h3>
<div class="scoreInfo">
    <ul>
        <li>参加考试人数：${count}</li>
        <li>总分：${sum}</li>
        <li>平均分：${ava}</li>
        <li>最高分：${max}</li>
        <li>最低分：${min}</li>
    </ul>
</div>


<form class="stuForm">
    <table class="stuTable">
        <tr>
            <th></th>
            <th class="process">考生学号</th>
            <th class="node">考生姓名</th>
            <th class="time">得分</th>
            <th class="operate">操作</th>
        </tr>

        <c:forEach  var="paper" items="${paperList}" varStatus="loop">
            <tr>
                <td id="num">${loop.count}</td>
                <td>
                    ${paper.userId}
                </td>
                <td>
                    ${userName[loop.count-1]}

                </td>
                <td>${paper.score}</td>
                <td>
                    <a href="${ctx}/score/toUpdateScore?paperId=${paper.paperId}&userId=${paper.userId}">修改</a>
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
        else{
            var htmltext="";
            $.ajax({
                type:'Post',
                url:'${ctx}/score/searchInfo',
                data:searchInfo,
                dataType:'json',
                success:function (data) {

                }
            })
        }
    }
    

</script>


</body>
</html>