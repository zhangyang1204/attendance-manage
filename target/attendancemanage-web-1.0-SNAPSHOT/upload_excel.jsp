<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--BootStrap的 css文件-->
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!--  Bootstrap 核心 JavaScript 文件 -->
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <title></title>
    <script>
        /*附件上传*/
        function upload() {
            // alert("aaa")
            var fileObj = document.getElementById("file").files[0]; // 获取文件对象
            var FileController = "/user/uploadExcel";                    // 接收上传文件的后台地址
            // FormData 对象
            var form = new FormData();
            /*form.append("name", "tom");  */                      // 可以增加表单数据
            form.append("file", fileObj);                           // 文件对象
            // XMLHttpRequest 对象
            var xhr = new XMLHttpRequest();
            xhr.open("post", FileController, true);
            xhr.onload = function () {
                alert("上传完成!");
                var i =
                ${id}
                if (i == 1) {
                    window.location.href = "/user/findAllStudents";
                } else {
                    window.location.href = "/user/findAllTeachers";
                }
            }
            xhr.send(form);

        }

        function chooseFile() {
            $("#file").click();
        }
        function fetchFileName () {
            //获取文件的value值
            file = $("#file").val()

            //获取文件名+扩展名
            fileName = file.split("\\").pop();
            /*!//获取文件名
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            //获取文件的扩展名
            fileExt = file.substr(file.lastIndexOf("."));*/

            console.log("filename:"+fileName);
            $("#fileNameInput").val(fileName);
        }
    </script>
    <script src="https://at.alicdn.com/t/font_2280902_esdsme300u7.js"></script>

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
<body style="text-align: center;width: 100%;height: 80%">
<div style="text-align: center;height: min-content">
        <input
               style="display: none"
               aria-describedby="basic-addon2"  type="file" size="27" id="file" accept=".xlsx"
                onchange="fetchFileName()"
               name="myfile"/>
    <div class="input-group" style="width: 50%;margin-left: 25%;margin-bottom: 20px;margin-top: 30px;">
        <input id="fileNameInput" readonly="readonly" type="text" onclick="chooseFile()" class="form-control" placeholder="选择文件"
               aria-describedby="basic-addon2">
        <span class="input-group-btn">
        <button onclick="chooseFile()" class="btn btn-default" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-sousuo"></use>
        </svg> 选择</button>
        <button onclick="upload()" class="btn btn-info" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-daochu-tianchong"></use>
        </svg> 上传</button>
      </span>
    </div>
</div>
</body>
</html>
