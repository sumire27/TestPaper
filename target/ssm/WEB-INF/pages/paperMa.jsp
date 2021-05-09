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

    <title>试卷管理页面</title>
</head>
<body>
<div class="addUser">
    <a href="${ctx}/admin/toAddPaper" class="add" onclick="addUser()"><span>+</span>添加</a>
</div>
<form class="stuForm">
    <table class="stuTable">
        <tr>
            <th class="name">试卷编号</th>
            <th class="operate">试卷名称</th>
            <th class="process">年级</th>
            <th class="process">对应科目</th>
            <th class="node">允许时长</th>
            <th class="operate">操作</th>
        </tr>
        <c:forEach  var="paper" items="${paperList}">
            <tr>
                <td>${paper.paperId}</td>
                <td>${paper.paperName}</td>
                <td>${paper.gradeId}</td>
                <td>${paper.courseId}</td>
                <td>${paper.allowTime}</td>
                <td>
                    <a id="deleteStu" href="javascript:;" onclick="deleteYes('${ctx}/admin/deletePaper?paperId=${paper.paperId}')">删除</a>
                    <a href="${ctx}/admin/toPaperDetail?paperId=${paper.paperId}">查看</a>
                </td>
            </tr>
            <tr><td colspan="6" id="line">  </td></tr>

        </c:forEach>
    </table>
</form>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
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