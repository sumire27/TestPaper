<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>考试系统后台管理</title>
        <link rel="stylesheet" href="../../css/left.css" />
        <link rel="stylesheet" href="../../css/top.css" />
        <link rel="stylesheet" href="../../css/center.css" />
    </head>
    <body>
    	<div class="main">
		<div class="top">
			<label class="text1">考试系统后台管理</label>
			<label class="userInfo">用户：<span class="user-name">${user.userName}</span></label>
		</div>
		<div class="left" id="left">
			<ul>
				<li class="userMa">
					<a >用户管理</a><br>
					<div class="userMa-detail">
						<a href="${ctx}/user/allStu" >学生管理</a><br>
						<a href="${ctx}/score/toScoreMa" >成绩管理</a>
					</div>
				</li>
				<li class="questionMa"><a>题库管理</a>
				<div class="userMa-detail">
					<a href="${ctx}/admin/allType" >题型管理</a><br>
					<a href="${ctx}/admin/allQuestion" >试题管理</a>
				</div>
				</li>
				<li class="basicMa"><a>基础管理</a>
				<div class="userMa-detail">
					<a href="${ctx}/admin/allCourse" >课程管理</a><br>
					<a href="${ctx}/admin/allGrade" >年级管理</a>
				</div>
				</li>

				<li class="paperMa"><a>试卷管理</a>
					<div class="userMa-detail">
						<a href="${ctx}/admin/allPaper" >试卷管理</a><br>

					</div>
				</li>
			</ul>
		</div>
			<div class="center">
				<iframe id="div_view" width="100%" height="100%"></iframe>
			</div>

		<div class="bottom"></div>
		</div>
		<script type="text/javascript" src="../../js/jquery.js"></script>
		<script type="text/javascript" src="../../js/common.js"></script>
		<script type="text/javascript" src="../../js/core.js"></script>
		<script type="text/javascript" src="../../js/jquery.dialog.js"></script>
		<script type="text/javascript" src="../../js/index.js"></script>
		<script type="text/javascript" src="../../js/bootstrap-treeview.js"></script>
<%--		iframe局部页面刷新--%>
		<script type="text/javascript">
		$(function(){
		     /* 点击事件会在超链接跳转前发生 */
			$("#left a").click(function(){
				var link = $(this).attr('href');
		      $('#div_view').attr('src', link);
				 var href = window.location.href;
			//	$('#div_view').location.href=href.substr(0, href.indexOf('#')) + link;
				window.location.href = href.substr(0, href.indexOf('#')) + '#'+link;
				 console.log( href.substr(0, href.indexOf('#')) + link);
				 return false;
		      });
		    });
		</script>
 	</body>



</html>