<%--
  Created by IntelliJ IDEA.
  User: 15679
  Date: 2021/3/5
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>测试树形菜单插件</title>
    <style type="text/css">
        *{ margin:0; padding:0; list-style:none;}
        body { margin:20px;}
        h2 { font-family:"黑体"; font-size:24px; text-align:center; line-height:32px;}
        h5 { font-size:12px; text-align:center; font-weight:normal; color:#666; line-height:28px;}
        #nav a { text-decoration:underline;color:#06c; font-size:14px; line-height:24px;}
        #nav ul{ margin-bottom:5px;}
        #nav strong{ color:#696;}
        #nav.dyn li ul{ display:none;}
        #nav.dyn li ul.show{ display:block;}
        #nav.dyn li{ padding-left:15px;}
        #nav.dyn li.parent{ background:url(${ctx}/images/user_23.gif) 5px 10px no-repeat;}
        #nav.dyn li.open{ background:url(${ctx}/images/user_23.gif) 5px -34px no-repeat;}
    </style>
    <script type="text/javascript" src="../../js/tree.js"></script>
</head>
<body>
<div class="container">
    <h2>测试</h2>
    <div class="parent1">

    </div>
    <div class="parent2">

    </div>
</div>
<ul id="nav">
    <li><a href="#">主页</a></li>
    <li><a href="#">产品</a>
        <ul>
            <li><a href="#">大类别一</a>
                <ul>
                    <li><a href="#">小类别一</a>
                        <ul>
                            <li><a href="#">次类别一</a></li>
                            <li><a href="#">次类别二</a></li>
                        </ul>
                    </li>
                    <li><a href="#">小类别二</a></li>
                </ul>
            </li>
            <li><a href="#">大类别二</a></li>
            <li><a href="#">大类别三</a>
                <ul>
                    <li><a href="#">小类别一</a></li>
                    <li><a href="#">小类别二</a></li>
                </ul>
            </li>
        </ul>
    </li>
    <li><a href="#">服务</a>
        <ul>
            <li><a href="#">大类别一</a></li>
            <li><a href="#">大类别二</a></li>
            <li><a href="#">大类别三</a></li>
        </ul>
    </li>
    <li><a href="#">合作</a></li>
    <li><a href="#">关于我们</a>
        <ul>
            <li><a href="#">大类别一</a>
                <ul>
                    <li><a href="#">小类别一</a></li>
                    <li><a href="#">小类别二</a></li>
                </ul>
            </li>
            <li><a href="#">大类别二</a>
                <ul>
                    <li><a href="#">小类别一</a></li>
                    <li><a href="#">小类别二</a></li>
                </ul>
            </li>
            <li><a href="#">大类别三</a>
                <ul>
                    <li><a href="#">小类别一</a></li>
                    <li><a href="#">小类别二</a></li>
                </ul>
            </li>
            <li><a href="#">大类别四</a></li>
        </ul>
    </li>
    <li><a href="#">联系我们</a>
        <ul>
            <li><a href="#">大类别一</a></li>
            <li><a href="#">大类别二</a></li>
        </ul>
    </li>
</ul>
<script type="text/javascript" src="../../js/jquery-2.0.3.js"></script>
<script type="text/javascript" src="../../js/bootstrap-treeview.js"></script>

<script type="text/javascript">


    function getTree() {
        var tree=[{text:"parent1",
            state:{
                expanded:false
            },
            tags: ['4'],
            nodes:[{text:"child1",nodes:[{text:"grandchid1"},{text:"grandchild2"}]},
                {text:"child2"}

            ]},{text:"paerent2",nodes:[{text:"child3"},{text:"child4"}]}]

        return tree;
    }
    $(".parent1").treeview({data:getTree(),
        backColor:'green'
    });
</script>
</body>
</html>
