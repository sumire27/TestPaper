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
    <a href="${ctx}/admin/toAddCourse" class="add" ><span>+</span>添加</a>
</div>
<form class="stuForm">
    <table class="stuTable">
        <tr>
            <th>编号</th>
            <th>课程名称</th>
            <th>操作</th>
        </tr>
        <c:forEach  var="course" items="${courseList}">
            <tr>
                <td>${course.courseId}</td>
                <td>${course.courseName}</td>
                <td>
                    <a id="deleteType" href="javascript:;" onclick="deleteYes('${ctx}/admin/deleteCourse?courseId=${course.courseId}')">删除</a>
                    <a href="${ctx}/admin/toUpdateCourse?courseId=${course.courseId}">编辑</a>
                </td>
            </tr>
            <tr><td colspan="3" id="line">  </td></tr>

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