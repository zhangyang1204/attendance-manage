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


    <script src="https://at.alicdn.com/t/font_2280902_esdsme300u7.js"></script>

    <script type="text/javascript">
        $(function (){
                if (${courses==null}) {
                    alert("未查询到相关信息");
                }
            }
        )
        function queryCourse(){
            var name = $("#course-name").val();
            window.location.href = "/data/findCourseByName?name=" + name+"&tid=${teacher.id}";
        }

        function exportExcel() {
            $.ajax({
                type:"post",
                url:"/data/exportCourseDetail",
                contentType:"application/json;charset=UTF-8",
                data:{},
                success: function (data) {
                    console.log("data:" + data);
                    var a = document.createElement('a');
                    var filename = '课程信息统计.xlsx';
                    a.href = data;
                    a.download = filename;
                    a.click();
                    alert("导出成功！");
                }
            })
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
<h3 style="margin-top: 30px;margin-bottom: 40px;margin-left: 10%    ">请选择课程：</h3>
<div class="input-group" style="width: 50%;margin-left: 25%;margin-bottom: 20px">
    <span class="input-group-addon" id="basic-addon3" style="background-color: white; border: white;font-size: medium;font-weight: bold;padding-right: 3px" >教师：${teacher.name}&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;课程：</span>
    <input  type="text" id="course-name" class="form-control" placeholder="输入课程名查询" aria-describedby="basic-addon2">
    <span class="input-group-btn">
        <button onclick="queryCourse()" class="btn btn-default" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-sousuo"></use>
        </svg> 查询</button>
        <button onclick="exportExcel()" class="btn btn-info" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-daochu-tianchong"></use>
        </svg> 导出</button>

      </span>
</div>
<form id="lform" style="clear: both"
>
    <table border="1px" class="table table-bordered table-hover" style="clear: both;width: 90%;margin-left: 5%">
        <tr style="background-color: rgb(66,139,202);color: white">
            <th style="width: 7%">编号</th>
            <th style="width: 15%">课程名</th>
            <th style="width: 7%">邀请码</th>
            <th style="width: 10%">学时</th>
            <th style="width: 10%">学生人数</th>
            <th style="width: 10%">签到次数</th>
            <th style="width: 8%">签到率</th>
            <th style="width: 10%">讨论次数</th>
            <th style="width: 10%">讨论率</th>
            <th style="width: 13%">操作</th>
        </tr>
        <c:forEach items="${courses}" var="course" varStatus="status">
            <tr class="teacher-item"  >
                <td>${status.count}</td>
                <td>${course.name}</td>
                <td>${course.invite_code}</td>
                <td>${course.hour}</td>
                <td>${course.studentCount}</td>
                <td>${course.signCount}</td>
                <td>${course.signRate}%</td>
                <td>${course.discussionCount}</td>
                <td>${course.discussionRate}%</td>
                <td><a href="/data/showSignDetail?cid=${course.cid}">查看签到详情>></a></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>