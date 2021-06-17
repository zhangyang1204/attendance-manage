<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8"/>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width,inital-scale=1"/>
    <title>用户登录</title>
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
            background-image: url(/static/img/bg.jpg);
            /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
            background-attachment: fixed;
            /* 让背景图基于容器大小伸缩 */
            background-size: cover;
            background-color: #CCCCCC;
            background-position: center center;
            background-repeat: no-repeat;
        }
    </style>

    <script type="text/javascript">

        //切换验证码
        function refreshCode() {
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "/login/checkCode?time=" + new Date().getTime();
        }

        window.onload=function () {


        }
    </script>
</head>
<body>
<%--.login .login_form .login_username{background:url(../images/login_yonghu.png) no-repeat 0 0;background-position: 15px 10px; height:40px;width:286px;padding-left:49px;padding-top:10px;padding-bottom:10px;line-height:20px}--%>
<%--.login .login_form .login_password{background:url(../images/login_mima.png) no-repeat 0 0;background-position: 15px 10px; height:40px;width:286px;padding-left:49px;padding-top:10px;padding-bottom:10px;line-height:20px}--%>
<%--<input style="display: none" value="${tip}"/>--%>
<ul class="nav nav-tabs success" style="background-color: cornflowerblue;">
    <li class="" style="float:left;margin-left:45%;margin-top: 5px;margin-bottom:5px;font-size:21px;font-weight: bolder;font-family:  黑体, Arial, Verdana, arial, serif">
        学生考勤管理系统
    </li>
</ul>
<div class="container"
     style="width: 500px;height: 600px;margin-top:50px;padding:50px;background-color: white;opacity: 0.95">
    <h3 style="text-align: center;font-weight: bold">登录</h3>
    <form action="/login/webLogin" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="account" class="form-control" id="user" placeholder="请输入用户名"/>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input style="width: 50%" type="text" name="verifycode" class="form-control" id="verifycode"
                   placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode();">
                <img src="/login/checkCode" title="看不清点击刷新" id="vcode"/>
            </a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;margin-top: 50px">
            <input style="width: 100%" class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <c:if test="${login_msg!=''&&login_msg!=null}">

        <div class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert">
                <span>&times;</span>
            </button>
            <strong>${login_msg}</strong>
        </div>
    </c:if>
</div>
</body>
</html>