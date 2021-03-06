<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div class="title"><h2>查看题型</h2></div>
<form action="${ctx}/admin/addType" method="post" name="myform" id="myform">
<div class="main">
    <p class="short-input ue-clear">
    	<label><span style="color:red">*</span>名称：</label>
        <input type="text" name="typeName" id="typeName" maxlength="10" value="${type.typeName }" disabled="disabled"/>
    </p>
    <p class="short-input ue-clear">
    	<label>分值：</label>
        <input type="text" id="score" name="score" value="${type.score }" disabled="disabled"/>
    </p>
    <p class="short-input ue-clear">
    	<label>备注：</label>
        <textarea id="remark" name="remark" disabled="disabled">${type.remark }</textarea>
    </p>
</div>
</form>
<div class="btn ue-clear">
    <a href="javascript:;" class="clear" onclick="returnList()">返回</a>
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

//情况所有
function returnList(){
	document.myform.attributes["action"].value = "${ctx}/admin/allType";
	$("form").submit();
}

showRemind('input[type=text], textarea','placeholder');
</script>
</html>