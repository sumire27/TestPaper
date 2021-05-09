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
    <title>查询结果</title>
</head>
<body>
<div class="addUser">
    <a href="${ctx}/admin/allQuestion" class="add">返回</a>

</div>
<form class="stuForm" method="post" action="${ctx}/admin/deleteQuestion">
    <table class="stuTable" id="stuTable">
        <tr>

            <th>编号</th>
            <th>题目名称</th>
            <th>对应科目</th>
            <th>题型</th>
            <th>难度</th>
            <th>操作</th>
        </tr>
        <c:forEach  var="question" items="${questionList}">
            <tr>

                <td>${question.questionId}</td>
                <td>${question.quesName}<br>${question.optionA}<br>${question.optionB}<br>${question.optionC}<br>${question.optionD}</td>
                <td>${question.courseId}</td>
                <td>${question.typeId}</td>
                <td>
                    <c:if test="${question.difficulty==0}">简单</c:if>
                    <c:if test="${question.difficulty==1}">中等</c:if>
                    <c:if test="${question.difficulty==2}">较难</c:if>
                </td>
                <td>
                    <a id="delete" href="javascript:;" onclick="deleteYes('${ctx}/admin/deleteQues?questionId=${question.questionId}')">删除</a>
                    <a href="${ctx}/admin/toUpdateQues?questionId=${question.questionId}">编辑</a>
                    <a href="${ctx}/admin/toQuesDetail?questionId=${question.questionId}">查看</a>
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
</script >
<script type="text/javascript">

        var otext ="";
          otext=  ${searchInfo};
        $('td:nth-child(3)').GL({
                ocolor: 'yellow',//设置关键词高亮颜色
                oshuru: otext//设置要显示的关键词

        });


</script>
</body>
</html>