<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta METAHTTP-EQUIV="Pragma" CONTENT="no-cache">

    <meta METAHTTP-EQUIV="Cache-Control" CONTENT="no-cache">

    <meta METAHTTP-EQUIV="Expires" CONTENT="0">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta charset="UTF-8">
    <title>后台管理</title>

    <!--BootStrap的 css文件-->
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <style>
        .left_page {
            width: 20%;
            height: 100%;
            display: inline-block;
            vertical-align: top;
            margin-top: 0px;
            margin-left: 0px;
            padding: 50px;
            background-color: lavender;
            text-align: center;
        }

        .right_page {
            vertical-align: top;
            display: inline-block;
            width: 75%;
            margin-top: 0px;
            margin-bottom: 100px;
            margin-left: 20px;
            padding-top: 50px;
            background-color: white;
            opacity: 0.85;
        }

        .catergory {
            font-size: 20px;
            font-weight: bold;
        }

        .title {
            font-size: 22px;
            font-weight: bold;
        }

        .dropdown-item {
            height: 30px;
            margin-top: 1px;
            text-align: center;
            /*垂直居中 */
            vertical-align: middle;
            width: 60%;
            margin-left: 20%;
            display: flex;
            background-color: cornflowerblue;
            /*border: blue solid 1px;*/
            justify-content: center; /*水平居中*/
        }

        .dropdown-item a {
            color: white;
            margin-bottom: 5px;
        }
    </style>
    <script>
        /*   function showTip() {
               alert("数据已成功导入");

           }*/
        function iframeAutoHeight() {
            var iframe = document.getElementById("midFrame");
            if (navigator.userAgent.indexOf("MSIE") > 0 || navigator.userAgent.indexOf("rv:11") > 0 || navigator.userAgent.indexOf("Firefox") > 0) {
                iframe.height = iframe.contentWindow.document.body.scrollHeight;
            } else {
                iframe.height = iframe.contentWindow.document.documentElement.scrollHeight;
            }
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs success" style="opacity: 0.8;background-color: cornflowerblue">
    <li style="font-size: 18px;font-weight: bold; color: black;margin-top: 8px ;margin-bottom:8px;margin-left:20px;font-family: '黑体'">
        后台管理
    </li>
    <!--    <li style="float: right;"><a href="manageIndex.jsp" style="font-size: 18px;font-weight: bold;color: #66afe9;font-family: '宋体'">后台管理</a></li>-->
</ul>
<div class="left_page">
    <div class="title">数据管理</div>
    <div style="margin-top: 20px;padding-bottom: 10px">
        <div class="dropdown">
            <div class="catergory dropdown-toggle">
                <a >管理学生信息<span class="caret"></span></a>
            </div>

            <div class="dropdown-item btn">
                <a href="/add_student.jsp" target="midFrame">添加学生</a>
            </div>
            <div class="dropdown-item btn">
                <a href="/user/deleteStudentInfo" target="midFrame">删除学生</a>
            </div>
            <div class="dropdown-item btn">
                <a href="/user/editStudentInfo" target="midFrame">修改学生</a>
            </div>
            <div class="dropdown-item btn">
                <a  href="/user/showStudentInfo" target="midFrame">查看学生</a>
            </div>
        </div>
    </div>
    <div style="margin-top: 20px;padding-bottom: 10px">
        <div class="dropdown">
            <div class="catergory dropdown-toggle">
                <a >管理教师信息<span class="caret"></span></a>
            </div>
            <div class="dropdown-item btn">
                <a href="/add_teacher.jsp" target="midFrame">添加教师</a>
            </div>
            <div class="dropdown-item btn">
                <a href="/user/deleteTeacherInfo" target="midFrame">删除教师</a>
            </div>
            <div class="dropdown-item btn">
                <a href="/user/editTeacherInfo" target="midFrame">修改教师</a>
            </div>
            <div class="dropdown-item btn">
                <a href="/user/showTeacherInfo" target="midFrame">查看教师</a>
            </div>
        </div>
    </div>
    <hr/>
    <div style="margin-bottom: 20px">
        <div class="dropdown">
            <div class="catergory dropdown-toggle text-primary" data-toggle="dropdown">
                <a>数据导入<span class="caret"></span></a>
            </div>
            <div class="dropdown-menu cater_list">
                <ul>
                    <li style="margin: 10px">
                        <a href="/user/importData?id=2" target="midFrame">导入教师信息</a>
                    </li>
                    <li style="margin-bottom: 10px;margin-left: 10px">
                        <a href="/user/importData?id=1" target="midFrame">导入学生信息</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <hr/>
    <div style="margin-bottom: 20px">
        <div class="dropdown">
            <div class="catergory dropdown-toggle text-primary" >
                <a href="/data/queryTeachers" target="midFrame" style="margin-right: 8px">数据统计</a>
            </div>
        </div>
    </div>
    <hr/>
</div>

</div>
<div class="right_page">
    <iframe name="midFrame" id="midFrame" src="/user/findAllStudents" frameborder="0" scrolling="no" width="100%"
            onload="iframeAutoHeight();"></iframe>
</div>
</body>
</html>
