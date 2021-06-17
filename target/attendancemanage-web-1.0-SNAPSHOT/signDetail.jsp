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
    <!-- 引入 echarts.js -->
    <script src="/static/js/echarts.min.js"></script>

    <script type="text/javascript">

    </script>
    <style type="text/css">

        td{
            text-align: center;
        }
        th{
            text-align: center;
        }
    </style>
</head>
<body>

<div class="input-group" style="width: 50%;margin-left: 25%;margin-bottom: 20px">
    <span class="input-group-addon" id="basic-addon3" style="background-color: white; border: white;font-size: medium;font-weight: bold;padding-right: 3px" >教师：${teacher.name}&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;课程：${course.name}</span>
    <%--<input  type="text" id="course-name" class="form-control" placeholder="输入课程名查询" aria-describedby="basic-addon2">
    <span class="input-group-btn">
        <button onclick="queryCourse()" class="btn btn-default" type="button"><svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-sousuo"></use>
        </svg> 查询</button>
      </span>--%>
</div>
<form id="lform" style="clear: both"
>
    <table border="1px" class="table table-bordered table-hover" style="clear: both;width: 80%;margin-left: 10%">
        <tr style="background-color: rgb(66,139,202);color: white">
            <th style="width: 25%">课程名</th>
            <th style="width: 15%">邀请码</th>
            <th style="width: 10%">学时</th>
            <th style="width: 10%">学生人数</th>
            <th style="width: 10%">签到次数</th>
            <th style="width: 10%">签到率</th>
            <th style="width: 10%">讨论次数</th>
            <th style="width: 10%">讨论率</th>
        </tr>
            <tr>
                <td>${course.name}</td>
                <td>${course.invite_code}</td>
                <td>${course.hour}</td>
                <td>${course.studentCount}</td>
                <td>${course.signCount}</td>
                <td>${course.signRate}%</td>
                <td>${course.discussionCount}</td>
                <td>${course.discussionRate}%</td>
            </tr>
    </table>
</form>
<div id="main"  style="width: 80%;height:450px;margin-left: 10%"></div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        title: {// 图表标题，可以通过show:true/false控制显示与否，还有subtext:'二级标题',link:'http://www.baidu.com'等
            text: '课程签到统计'
        },
        tooltip : {// 这个是鼠标浮动时的工具条，显示鼠标所在区域的数据，trigger这个地方每种图有不同的设置，见官网吧，一两句说不清楚
            trigger: 'axis'
        },
        legend: {// 这个就是图例，也就是每条折线或者项对应的示例，就是这个<a target=_blank href="https://img-blog.csdn.net/20160622094820180?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center">图例</a>
            data:[]
        },
        toolbox: {
            feature: {
                saveAsImage: {}// 工具，提供几个按钮，例如动态图表转换，数据视图，保存为图片等
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',// 这几个属性可以控制图表上下左右的空白尺寸，可以自己试试。
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : []// X轴的定义
            }
        ],
        yAxis : [
            {
                type : 'value'// Y轴的定义
            }
        ],
        series : []// 这里就是数据了
    };
    var isShowAllData = true;
    //加载数据
    $.ajax({
        url:"/data/findLineData?cid=${cid}",
        type:'get',
        contentType:"application/json;charset=UTF-8",
        success:function(jsons){
            var Item = function(){
                return {
                    name:'',
                    type:'line',
                    // itemStyle: {normal: {areaStyle: {type: 'default',opacity:isArea}}},
                    label: {normal: {show: isShowAllData,position: 'top'}},
                    // markLine: {data: [{type: 'average', name: '平均值'}]},
                    data:[]
                }
            };
            var legends = [];
            var Series = [];
            var jsonObj = eval('('+jsons+')')
            var json = jsonObj.data;
            console.log("jsonObj=" + jsonObj);
            console.log("json=" + json);
            // for(var i=0;i < json.length;i++){
                var it = new Item();
                it.name = json.name;
                legends.push(json.name);
                it.data = json.data;
                Series.push(it);
            // }

            option.xAxis[0].data = jsonObj.xContent;
            option.legend.data = legends;
            option.series = Series; // 设置图表
            myChart.setOption(option);// 重新加载图表
        },
        error:function(){
            alert("数据加载失败！请检查数据链接是否正确");
        }
    });


    myChart.setOption(option);
</script>

</body>
</html>