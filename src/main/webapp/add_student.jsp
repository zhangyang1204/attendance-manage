<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width,inital-scale=1"/>
    <title></title>
    <!--BootStrap的 css文件-->
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <%--    <link rel="stylesheet" type="text/css" href="css/pageStyle.css">--%>
    <style type="text/css">
        html {
            width: 100%;
            height: 100%;
        }

        body {
            width: 100%;
            height: 100%;
            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            /*  background-attachment: fixed;
              !* 让背景图基于容器大小伸缩 *!
              background-size: cover;
              background-color: #CCCCCC;
              background-position: center center;
              background-repeat: no-repeat;*/
        }
    </style>

    <script type="text/javascript">
        function setMajorValue() {
           var m = $("#sMajor option:selected").text();
            console.log("major", m);
            $("#major").val(m);
            console.log("#major input",  $("#major").val());
        }
        function setClazzValue() {
           var m = $("#sClazz option:selected").text();
            $("#clazz").val(m);
        }
    </script>
</head>
<body>
<div class="container"
     style="width: 300px;">
    <h3 style="text-align: center;font-weight: bold">添加学生</h3>
    <form action="/user/addStudent" method="post">
        <div class="form-group">
            <label for="user">姓名：</label>
            <input type="text" name="name"  class="form-control" id="user"/>
        </div>
        <div class="form-group">

            <label for="price">性别：</label>
            <input name="sex"  class="form-control" id="price"/>
        </div>
        <div class="form-group">
            <label for="description">年龄：</label>
            <input name="age"  class="form-control" id="description"/>
        </div>
        <div class="form-group">
            <label for="sMajor">专业：</label>
            <select id="sMajor" onchange="setMajorValue()">
                <option>计算机科学与技术</option>
                <option>会计</option>

            </select>
            <input name="major" style="display: none" value="计算机科学与技术" class="form-control" id="major"/>
        </div>
        <div class="form-group">
            <label for="sClazz">班级：</label>
            <select id="sClazz" onchange="setClazzValue()">
                <option>171班</option>
                <option>172班</option>
                <option>181班</option>
                <option>182班</option>
                <option>191班</option>
                <option>192班</option>
                <option>201班</option>
                <option>202班</option>
            </select>
            <input name="clazz" style="display: none" value="171班" class="form-control" id="clazz"/>
        </div>
        <div class="form-group">
            <label for="student_number">学号：</label>
            <input name="student_number"  class="form-control" id="student_number"/>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input name="password"  class="form-control" id="password"/>
        </div>
        <div class="form-group">
            <label for="idcard_number">身份证号：</label>
            <input name="idcard_number" class="form-control" id="idcard_number"/>
        </div>
        <input name="headimg_url" style="display: none" value="/static/img/head.jpg"  class="form-control" id="headimg_url"/>
       <%-- <div class="form-group">
            <label for="major">专业：</label>
            <input name="major" value="${student.major}" class="form-control" id="major"/>
        </div>--%>
        </div><div class="form-group" style="text-align: center;margin-top: 50px">
            <input style="width: 100px" class="btn btn btn-primary" type="submit" value="提交">
        </div>
    </form>
</div>
</body>
</html>