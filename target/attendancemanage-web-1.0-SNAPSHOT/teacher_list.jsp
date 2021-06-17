<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">

        /*function logout() {
            if(confirm("确定要退出登录吗？")){
                <%--location.href="${pageContext.request.contextPath}/logoutServlet"--%>
            }}*/
    </script>
    <script src="https://at.alicdn.com/t/font_2280902_esdsme300u7.js"></script>


    <style type="text/css">
        .delete {
            color: red;
        }

        .edit {
            color: dodgerblue;
            margin-left: 10px;
        }

        .icon {
            width: 1em;
            height: 1em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }

        td {
            text-align: center;
        }

        th {
            text-align: center;
        }
    </style>
    <script>
        $(function () {
            $("#firstCb").click(function () {
                var cbs = $("input[name='lid']");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }
            })
            $("#delSelectLog").click(function () {
                if (confirm("确定要删除选中的条目吗？")) {
                    var cbs = document.getElementsByName("lid");
                    var flag = false;
                    //判断选择条目是否为空
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            flag = true;
                            break;
                        }
                    }
                    //选择条目非空，提交表单
                    if (flag) {
                        document.getElementById("lform").submit();
                    }
                }
            })
            if (${teachers==null}) {
                alert("未查询到相关信息");
            }
        })

        function queryTeacher() {
            var name = $("#teacher-name").val();
            window.location.href = "/user/findTeacherByName?name=" + name;
        }

    </script>
</head>
<body>
<%--<div style="color:white;width: 100% ;margin-top: 30px;text-align: center;font-size:26px;font-weight: bolder">--%>
<%--    <span>网站访问记录</span>--%>
<%--</div>--%>
<c:if test="${delete==null&&edit==null}">
    <div class="input-group" style="width: 40%;margin-left: 30%;margin-bottom: 20px;margin-top: 50px;">
    <span class="input-group-addon" id="basic-addon3"
          style="background-color: white; border: white;font-size: medium;font-weight: bold;padding-right: 3px">教师:</span>
        <input type="text" id="teacher-name" class="form-control" placeholder="输入教师名查询" aria-describedby="basic-addon2">
        <span class="input-group-btn">
        <button onclick="queryTeacher()" class="btn btn-default" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-sousuo"></use>
        </svg>查询</button>
      </span>
    </div>
</c:if>>

<c:if test="${delete!=null}">
    <div style="float: right;margin-right: 15%;margin-bottom: 10px">
        <a class="  btn btn-primary text-warning" href="javascript:void(0)" id="delSelectLog">删除选中</a>
    </div>
</c:if>

<form id="lform" style="clear: both" action="/user/deleteSelectedTeachers"
      method="post">
    <table border="1px" class="table table-bordered table-hover" style="clear: both;width: 70%;margin-left: 15%">
        <tr style="background-color: rgb(66,139,202);color: white">
            <c:if test="${delete!=null}">
                <th style="width: 5%"><input type="checkbox" id="firstCb"></th>
            </c:if>
            <th style="width: 5%">编号</th>
            <th style="width: 15%">姓名</th>
            <th style="width: 15%">性别</th>
            <th style="width: 10%">年龄</th>
            <th style="width: 15%">密码</th>
            <th style="width: 20%">身份证号</th>
            <c:if test="${delete!=null||edit!=null}">
            <th style="width: 15%">操作</th>
            </c:if>
        </tr>
        </tr>
        <c:forEach items="${teachers}" var="info" varStatus="status">
            <tr>
                <c:if test="${delete!=null}">
                    <th><input type="checkbox" name="lid" value="${info.id}"></th>
                </c:if>
                <td>${status.count}</td>
                <td>${info.name}</td>
                <td>${info.sex}</td>
                <td>${info.age}</td>
                <td>${info.password}</td>
                <td>${info.idcard_number}</td>
                <c:if test="${delete!=null||edit!=null}">
                <td>
                    <c:if test="${delete!=null}">
                    <a href="/user/deleteTeacherById?id=${info.id}" class="delete">删除</a>
                    </c:if>
                    <c:if test="${edit!=null}">
                    <a href="/user/editTeacher?id=${info.id}&name=${info.name}&sex=${info.sex}&age=${info.age}&password=${info.password}&img_url=${info.img_url}&idcard_number=${info.idcard_number}"
                       class="edit">编辑</a>
                    </c:if>
                </td>
                </c:if>
            </tr>
        </c:forEach>
        </tr>
    </table>
</form>
</body>
</html>