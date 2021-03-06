<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${ctx}/css/base.css" />
<link rel="stylesheet" href="${ctx}/css/info-reg.css" />
<link rel="stylesheet" href="${ctx}/css/jquery.searchableSelect.css" />
<title>移动办公自动化系统</title>
</head>

<body>
<div class="title"><h2>编辑年级信息</h2></div>
<form action="${ctx}/admin/updateGrade" method="post" name="myform" id="myform">
<div class="main">
    <p class="short-input ue-clear">
        <label><span style="color:red">*</span>年级编号：</label>
        <input type="text" name="gradeId" id="gradeId" maxlength="10" value="${grade.gradeId}" readonly="readonly"/>
    </p>
    <p class="short-input ue-clear">
    	<label><span style="color:red">*</span>年级名称：</label>
        <input type="text" name="gradeName" id="gradeName" maxlength="10" value="${grade.gradeName}" readonly="readonly"/>
    </p>
    
    <p class="short-input ue-clear">
    	<label><span style="color:red">*</span>已包含课程</label>
        <c:forEach items="${dataList}" var="o">
            <p class="short-input ue-clear">
            <label><span style= "width:100px "> &nbsp; </span></label>
            <input type="checkbox" name="courseId" id="courseId" value="${o.courseId}"
              ${fn:contains(courseNames,o.courseName)? 'checked':""}
            />${o.courseName}
            </p>
        </c:forEach>
    </p>
    
</div>
</form>
<div class="btn ue-clear">
    <a href="javascript:;" class="confirm" onclick="addGrade()">确定</a>
    <a href="${ctx}/admin/allGrade" class="clear">返回</a>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.searchableSelect.js"></script>
<script type="text/javascript">
$(function(){
	$("#grade").searchableSelect();
});
$(".select-title").on("click",function(){
	$(".select-list").toggle();
	return false;
});
$(".select-list").on("click","li",function(){
	var txt = $(this).text();
	$(".select-title").find("span").text(txt);
});

//注册
function addGrade(){
	//document.myform.attributes["action"].value = "${ctx}/addGrade.action";
	$("form").submit();
}

/* //情况所有
function returnList(){
	document.myform.attributes["action"].value = "${ctx}/toTypePage.action"; 
	$("form").submit();
} */

showRemind('input[type=text], textarea','placeholder');
</script>
</html>