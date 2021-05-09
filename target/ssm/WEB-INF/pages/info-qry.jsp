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
    <title>移动办公自动化系统</title>
</head>

<body>
<div class="title"><h2>查看用户信息</h2></div>
<form action="${ctx}/user/updateUser" method="post" name="myform" id="myform">
    <div class="main">
        <p class="short-input ue-clear">
            <label><span style="color:red">*</span>学号：</label>
            <input type="text" name="userId" id="userid" value="${user.userId }"/>
        </p>
        <p class="short-input ue-clear">
            <label><span style="color:red">*</span>姓名：</label>
            <input type="text" placeholder="用户昵称" name="userName" id="username" value="${user.userName }"/>
        </p>
        <p class="short-input ue-clear">
            <label><span style="color:red">*</span>年级：</label>
            <input type="text" placeholder="请输入年级" name="grade" id="grade" value="${gradeName }"/>
        </p>
        <p class="short-input ue-clear">
            <label><span style="color:red">*</span>邮箱：</label>
            <input type="text" onfocus="checkEmail()" id="email" name="email" value="${user.email }"/>
        </p>
        <p class="short-input ue-clear">
            <label>联系电话：</label>
            <input type="text" onfocus="checkPhone()" id="telephone" name="telephone" value="${user.telephone }"/>
        </p>
        <p class="long-input ue-clear">
            <label>地址：</label>
            <input type="text" id="address" name="address" value="${user.address }"/>
        </p>
        <p class="short-input ue-clear">
            <label>备注：</label>
            <textarea id="remark" name="remark">${user.remark }</textarea>
        </p>
    </div>
</form>
<div class="btn ue-clear">
    <a href="${ctx}/user/allStu" class="clear">返回</a>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/WdatePicker.js"></script>
<script type="text/javascript">
    $(".select-title").on("click",function(){
        $(".select-list").toggle();
        return false;
    });
    $(".select-list").on("click","li",function(){
        var txt = $(this).text();
        $(".select-title").find("span").text(txt);
    });

    function checkPhone(){

    }

    //注册
    function addUser(){
        $("form").submit();
    }

    //情况所有
    function clearAll(){
        alert("清空所有内容");
    }

    showRemind('input[type=text], textarea','placeholder');
</script>
</html>