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

    <title>学生查询结果</title>
</head>
<body>
<div class="addUser">
    <a href="${ctx}/user/allStu" class="">返回</a>

</div>
<form class="stuForm" method="post" >
    <table class="stuTable" id="stuTable">
        <tr>
            <th>用户名</th>
            <th>姓名</th>
            <th>年级</th>
            <th>电话</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        <c:forEach  var="user" items="${userList}">
            <tr>
                <td>${user.userId}</td>
                <td>${user.userName}</td>
                <td>${user.grade}</td>
                <td>${user.telephone}</td>
                <td>${user.address}</td>
                <td>
                    <a id="delete" href="javascript:;" onclick="deleteYes('${ctx}/user/deleteStu?userId=${user.userId}')">删除</a>
                    <a href="${ctx}/user/toUpdateStu?userId=${user.userId}">编辑</a>
                    <a href="${ctx}/user/toDetail?userId=${user.userId}">查看</a>
                </td>
            </tr>
            <tr><td colspan="6"  id="line">  </td></tr>
        </c:forEach>
    </table>
</form>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/gaoliang.js"></script>
<script type="text/javascript">
    function deleteYes(url) {
        var r=confirm("你确定要删除吗");
        if (r==true)
            window.location.href=url;
        else
            return;

    }



    function addUser(url){
        window.location.href=url;

    }

</script >

</body>
</html>