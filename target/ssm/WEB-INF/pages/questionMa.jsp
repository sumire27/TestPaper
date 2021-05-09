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
    <link rel="stylesheet" href="${ctx}/css/quesMa.css" />
    <title>试题管理页面</title>
</head>
<body>
<div class="addUser">
    <a href="javascript:;" onclick="addUser('${ctx}/admin/toAddQues')" class="add" ><span>+</span>添加</a>

    <a href="javascript:;" class="del" onclick="deleteUser('${ctx}/admin/deleteQuestion')"><span>-</span>删除</a>

</div>
<div class="search-box">
    <input type="text" class="searchInfo" name="searchInfo">
    <input type="button" class="searchBtn" name="searchBtn" value="搜索" onclick="search('${ctx}/admin/searchInfo')">
</div>
<form class="stuForm" method="post" action="$n">
    <table class="stuTable">
        <tr>

            <th>编号</th>
            <th>题目名称</th>
            <th>对应科目</th>
            <th>题型</th>
            <th>年级</th>
            <th>操作</th>
        </tr>
        <c:forEach  var="question" items="${questionList}">
            <tr>

            <td>${question.questionId}</td>
            <td>${question.quesName}<br>${question.optionA}<br>${question.optionB}<br>${question.optionC}<br>${question.optionD}</td>
            <td>${question.courseId}</td>
            <td>${question.typeId}</td>
            <td>
                    ${question.gradeId}
            </td>
            <td>
                <a id="delete" href="javascript:;" onclick="deleteYes('${ctx}/admin/deleteQues?questionId=${question.questionId}')">删除</a>
                <a href="${ctx}/admin/toUpdateQues?questionId=${question.questionId}">编辑</a>
                <a href="${ctx}/admin/toQuesDetail?questionId=${question.questionId}">查看</a>
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
    function deleteUser(url){
        var ids = "";
        $("input:checkbox[name='questionId']:checked").each(function() {
            ids += $(this).val() + ",";
        });
        //判断最后一个字符是否为逗号，若是截取
        var id = ids.substring(ids.length -1, ids.length);
        if(id == ","){
            ids = ids.substring(0, ids.length-1);
        }
        window.location.href=url+'?quesionId='+ids;
    }

    function addUser(url){
        window.location.href=url;

    }

    function search(url) {
        var searchInfo=$('.searchInfo').val();
        if (searchInfo==null)
            alert("输入为空!")
        else
            window.location.href=url+'?searchInfo='+searchInfo;
    }

    $('.del').hide();
    $(document).ready(function () {
        $("input:checkbox[name='questionId']").each(function () {
            $("input:checkbox[name='questionId']").click(function () {
            if($("input:checkbox[name='questionId']").prop("checked")){
                $('.del').show();
            }else{
                $('.del').hide();
            }
        })
        })

    })
</script>
</body>
</html>