<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <meta METAHTTP-EQUIV="Pragma" CONTENT="no-cache">

    <meta METAHTTP-EQUIV="Cache-Control" CONTENT="no-cache">

    <meta METAHTTP-EQUIV="Expires" CONTENT="0">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta charset="UTF-8">

    <!--BootStrap的 css文件-->
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>


    <script src="https://at.alicdn.com/t/font_2280902_qv630bggktl.js"></script>

    <script type="text/javascript">
      /*  $(".teacher-item").click(function (){
            alert("aaa");
            var id = this.children[0].val;
            alert(id);
        })*/
        $(function (){
            if (${teachers==null}) {
                alert("未查询到相关信息");
            }
        }
        )
        function queryTeacher(){
            var name = $("#teacher-name").val();
            // alert(name);
            window.location.href = "/data/findTeacherByName?name=" + name;
        }
    </script>
    <style type="text/css">
        .icon {
            width: 1em; height: 1em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
        td{
            text-align: center;
        }
        th{
            text-align: center;
        }
    </style>
</head>
<body>
<h3 style="margin-top: 30px;margin-bottom: 40px;margin-left: 10%    ">请选择教师：</h3>
<div class="input-group" style="width: 50%;margin-left: 25%;margin-bottom: 20px">
    <span class="input-group-addon" id="basic-addon3" style="background-color: white; border: white;font-size: medium;font-weight: bold" >教师：</span>
    <input  type="text" id="teacher-name" class="form-control" placeholder="输入教师姓名查询" aria-describedby="basic-addon2">
    <span class="input-group-btn">
        <button onclick="queryTeacher()" class="btn btn-default" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-sousuo"></use>
        </svg> 查询</button>
      </span>
</div>
<form id="lform" style="clear: both"
     >
    <table border="1px" class="table table-bordered table-hover" style="clear: both;width: 80%;margin-left: 10%">
        <tr style="background-color: rgb(66,139,202);color: white">
            <th style="width: 10%">编号</th>
            <th style="width: 15%">姓名</th>
            <th style="width: 15%">性别</th>
            <th style="width: 10%">年龄</th>
            <th style="width: 15%">密码</th>
            <th style="width: 20%">身份证号</th>
            <th style="width: 15%">操作</th>
        </tr>
        <c:forEach items="${teachers}" var="info" varStatus="status">
            <tr class="teacher-item"  >
                <td>${status.count}</td>
                <td>${info.name}</td>
                <td>${info.sex}</td>
                <td>${info.age}</td>
                <td>${info.password}</td>
                <td>${info.idcard_number}</td>
<%--                <td style="padding: 0px ;background-color: "><button class="btn btn-info " style="width: 80%;height: 90%">选择</button></td>--%>
                <td><a href="/data/queryCourses?id=${info.id}">选择</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
