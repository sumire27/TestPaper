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
    <link rel="stylesheet" href="${ctx}/css/scoreMa.css" />
    <script type="text/javascript" src="${ctx}/js/jquery-2.0.3.js"></script>
    <title>查询结果</title>
</head>
<body>
<div class="addUser">
    <a href="${ctx}/score/toScoreMa" class="add">返回</a>

</div>

<form class="stuForm" method="post" action="${ctx}/user/deleteStu">

    <select name="grade" id="grade" onchange="select(this.options[this.options.selectedIndex].value)" >
        <option value="0">全部</option>
    <c:forEach items="${gradeList}" var="grade">
        <option value="${grade.gradeId}">${grade.gradeName}</option>
    </c:forEach>
    </select>



                        <table class="stuTable" id="stuTable">
                        <tr>
                        <th>试卷编号</th>
                        <th>试卷名</th>
                        <th>课程</th>
                        <th>年级</th>
                        <th>操作</th>
                        </tr>
                            <c:forEach items="${paperList}" var="paper" varStatus="loop">
                                <tr>
                                    <td>${paper.paperId}</td>
                                    <td>${paper.paperName}</td>
                                    <td>${courseList[loop.count-1].courseName}</td>
                                    <td>${gradeList[loop.count-1].gradeName}</td>
                                    <td><a href="${ctx}/score/scoreMa?paperId=${paper.paperId}">成绩管理</a></td>
                                </tr>
                                <tr><td colspan="5" id="line">  </td></tr>
                            </c:forEach>


                        </table>
                        </form>

                        <script type="text/javascript">
                        function deleteYes(url) {
                            var r=confirm("你确定要删除吗");
                            if (r==true)
                                window.location.href=url;
                            else
                                return;

                        }



                    function addUser(url){
                        window.location.href=url;

                    }

                    function search(url) {
                        var searchInfo=$('.searchInfo').val();
                        var option=$("#select-grade option:selected");
                        var grade=option.val();
                        if (searchInfo==null)
                            alert("输入为空!")
                        else
                            window.location.href=url;
                    }

                    function select(grade){
                        $.ajax({
                            type:"Post",
                            url:"${ctx}/score/select",
                            data:grade,
                            dataType:"text",
                            success:function (data1) {
                                var htmltext="<tr>   <th>试卷编号</th> <th>试卷名</th>     <th>课程</th> <th>年级</th> <th>操作</th> </tr>";
                                var data=$.parseJSON(data1);
                    $.each(data,function (k,v) {
                        console.log("键值"+k);
                        console.log("内容"+v.userId);
                        htmltext+="<tr> <td>"+v.paperId+"</td> <td>"+v.paperName+"</td><td>"+v.courseId+"</td><td>"+v.gradeId+"</td> <td><a href='${ctx}/score/scoreMa?paperId=?'"+v.paperId+">成绩管理</a></td> </tr>"

                    });
                        $('table ').html(""+htmltext+"<tr><td colspan=\"5\"  id=\"line\">  </td></tr>");
    },
                error:function (data2) {
                    alert("不存在");
                }
            })


    }

    function selectCourse(course){
        $.ajax({
            type:"Post",
            url:"${ctx}/score/select",
            data:course,
            dataType:"text",
            success:function (data1) {
                var htmltext="<tr>   <th>试卷编号</th> <th>试卷名</th>     <th>课程</th> <th>年级</th> <th>操作</th> </tr>";
                var data=$.parseJSON(data1);
                $.each(data,function (k,v) {
                    console.log("键值"+k);
                    console.log("内容"+v.userId);
                    htmltext+="<tr> <td>"+v.paperId+"</td> <td>"+v.paperName+"</td><td>"+v.courseId+"</td><td>"+v.gradeId+"</td> <td><a href='${ctx}/score/scoreMa?userId=?'"+v.userId+">成绩管理</a></td> </tr>"

                });
                $('table ').html(""+htmltext+"<tr><td colspan=\"5\"  id=\"line\">  </td></tr>");



            },
            error:function (data2) {
                alert("不存在");
            }
        })


    }


</script >


</body>
</html>