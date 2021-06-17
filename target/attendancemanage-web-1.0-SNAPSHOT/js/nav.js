var navs = [/*{
	"title" : "后台首页",
	"icon" : "icon-computer",
	"href" : "page/main.html",
	"spread" : false
},{
	"title" : "文章列表",
	"icon" : "icon-text",
	"href" : "page/news/newsList.html",
	"spread" : false
},{
	"title" : "友情链接",
	"icon" : "icon-text",
	"href" : "page/links/linksList.html",
	"spread" : false
},{
	"title" : "404页面",
	"icon" : "&#xe61c;",
	"href" : "page/404.html",
	"spread" : false
},{
	"title" : "系统基本参数",
	"icon" : "&#xe631;",
	"href" : "page/systemParameter/systemParameter.html",
	"spread" : false
},*/{
	"title" : "教师信息管理",
	"icon" : "&#xe61c;",
	"href" : "",
	"spread" : true,
	"children" : [
		{
			"title" : "添加教师",
			"icon" : "&#xe631;",
			"href" : "/add_teacher.jsp",
			"spread" : false
		},{
			"title" : "删除教师",
			"icon" : "&#xe631;",
			"href" : "/user/deleteTeacherInfo",
			"spread" : false
		},{
			"title" : "修改教师",
			"icon" : "&#xe631;",
			"href" : "/user/editTeacherInfo",
			"spread" : false
		},{
			"title" : "查看教师",
			"icon" : "&#xe631;",
			"href" : "/user/showTeacherInfo",
			"spread" : false
		}

	]
},
	{
	"title" : "学生信息管理",
	"icon" : "&#xe61c;",
	"href" : "",
	"spread" : true,
	"children" : [
		{
			"title" : "添加学生",
			"icon" : "&#xe631;",
			"href" : "/add_student.jsp",
			"spread" : false
		},{
			"title" : "删除学生",
			"icon" : "&#xe631;",
			"href" : "/user/deleteStudentInfo",
			"spread" : false
		},{
			"title" : "修改学生",
			"icon" : "&#xe631;",
			"href" : "/user/editStudentInfo",
			"spread" : false
		},{
			"title" : "查看学生",
			"icon" : "&#xe631;",
			"href" : "/user/showStudentInfo",
			"spread" : false
		}
	]
},{
		"title" : "数据导入",
		"icon" : "&#xe61c;",
		"href" : "",
		"spread" : true,
		"children" : [
			{
				"title" : "导入学生数据",
				"icon" : "&#xe631;",
				"href" : "/user/importData?id=1",
				"spread" : false
			},{
				"title" : "导入教师数据",
				"icon" : "&#xe631;",
				"href" : "/user/importData?id=2",
				"spread" : false
			}
		]
	},{
		"title" : "课程数据统计",
		"icon" : "&#xe631;",
		"href" : "/data/queryTeachers",
		"spread" : false
	}
]