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

        <title>学生管理页面</title>
    </head>
    <body>
    <div class="addUser">
        <a href="${ctx}/user/toAddUser" class="add" onclick="addUser()"><span>+</span>添加</a>
    </div>
    <div class="search-box">
        <input type="text" class="searchInfo" name="searchInfo">
        <input type="button" class="searchBtn" name="searchBtn" value="搜索" onclick="search('${ctx}/user/searchInfo')">
    </div>

    <form class="stuForm">
    <table class="stuTable">
        <tr>
            <th class="name">学号</th>
            <th class="name">姓名</th>
            <th class="process">年级</th>
            <th class="node">邮箱</th>
            <th class="time">联系电话</th>
            <th class="operate">操作</th>
        </tr>
    <c:forEach  var="stu" items="${stuList}">
        <tr>
            <td>${stu.userId}</td>
            <td>${stu.userName}</td>
            <td>
               ${stu.grade}
            </td>
            <td>${stu.email}</td>
            <td>${stu.telephone}</td>
            <td>
                <a id="deleteStu" href="javascript:;" onclick="deleteYes('${ctx}/user/deleteStu?userId=${stu.userId}')">删除</a>
                <a href="${ctx}/user/toUpdateStu?userId=${stu.userId}">编辑</a>
                <a href="${ctx}/user/toDetail?userId=${stu.userId}">查看</a>
            </td>
        </tr>
        <tr><td colspan="6" id="line">  </td></tr>

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

       function search(url) {
           var searchInfo=$('.searchInfo').val();
           if (searchInfo==null)
               alert("输入为空!")
           else
               window.location.href=url+'?searchInfo='+searchInfo;
       }
    </script>

    <script type="text/javascript">
        function select(option){

            $.ajax({
                type:"Post",
                url:"${ctx}/user/select",
                data:option,
                dataType:"text",
                success:function (data1) {
                    var htmltext="<tr>   <th>账号</th> <th>昵称</th><th>账户类型</th>  <th>邮箱</th> <th>联系电话</th><th>操作</th> </tr>";
                    var data=$.parseJSON(data1);
                    $.each(data,function (k,v) {
                        console.log("键值"+k);
                        console.log("内容"+v.userId);
                        htmltext+="<tr> <td>"+v.userId+"</td> <td>"+v.userName+"</td><td>"+v.userType+"</td> <td><a href='${ctx}/score/scoreMa?userId=?'"+v.userId+">成绩管理</a></td> </tr>"

                    });


                    $('table ').html(""+htmltext+"<tr><td colspan=\"4\"  id=\"line\">  </td></tr>");



                },
                error:function (data2) {
                    alert("不存在");
                }
            })


        }

    </script>
 	</body>
</html>