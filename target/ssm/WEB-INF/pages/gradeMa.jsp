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
    <title>课程管理页面</title>
</head>
<body>
<div class="addUser">
    <a href="${ctx}/admin/toAddGrade" class="add" ><span>+</span>添加</a>
</div>
<form class="stuForm">
    <table class="stuTable">
        <tr>
            <th class="num">年级编号</th>
            <th class="name">年级名称</th>
            <th class="process">包含课程</th>
            <th class="operate">操作</th>
        </tr>
        <c:forEach  var="grade" items="${gradeList}">
            <tr>
                <td>${grade.gradeId}</td>
                <td>${grade.gradeName}</td>
                <td>${grade.courseId}</td>
                <td>
                    <a id="deleteType" href="javascript:;" onclick="deleteYes('${ctx}/admin/deleteGrade?gradeId=${grade.gradeId}')">删除</a>
                    <a href="${ctx}/admin/toUpdateGrade?gradeId=${grade.gradeId}">编辑</a>
                    <a href="${ctx}/admin/toGradeDetail?gradeId=${grade.gradeId}">查看</a>

                </td>
            </tr>
            <tr><td colspan="4" id="line">  </td></tr>

        </c:forEach>
    </table>
</form>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    function deleteYes(url) {
        var r=confirm("你确定要删除吗");
        if (r==true)
            window.location.href=url;
        else
            return;

    }

</script>
</body>
</html>