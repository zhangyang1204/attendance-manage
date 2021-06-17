package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.*;
import cn.zyity.attendancemanage.dao.ICourseDao;
import cn.zyity.attendancemanage.dao.IDiscussionDao;
import cn.zyity.attendancemanage.dao.ISignInDao;
import cn.zyity.attendancemanage.dao.IUserDao;
import cn.zyity.attendancemanage.util.ExcelUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/data")
@Controller

public class DataController {
    @Autowired
    IUserDao dao;
    @Autowired
    ICourseDao courseDao;

    @Autowired
    ISignInDao signInDao;

    @Autowired
    IDiscussionDao discussionDao;

    @RequestMapping("/getSignCount")
    @ResponseBody
    public String getSignCount() {
        ArrayList<ChartsBean> list = new ArrayList<>();
        ChartsBean chartsBean = new ChartsBean("12.27", 20);
        ChartsBean chartsBean1 = new ChartsBean("12.28", 30);
        ChartsBean chartsBean2 = new ChartsBean("12.29", 10);
        list.add(chartsBean);
        list.add(chartsBean1);
        list.add(chartsBean2);
        return list.toString();
    }

    @RequestMapping("/queryTeachers")
    public String findTeachers(HttpServletRequest request) {
        List<Teacher> allTeachers = dao.findAllTeachers();
        request.setAttribute("teachers", allTeachers);
        return "forward:/chooseTeacher.jsp";
    }

    @RequestMapping("/queryCourses")
    public String findCourses(HttpServletRequest request, int id) {
        List<Course> allCourseByTeacherId = courseDao.findAllCourseByTeacherId(id);
        List<Teacher> teacherList = dao.findTeacherById(id);
        Teacher t = teacherList.get(0);
        request.getSession().setAttribute("teacher", t);
        ArrayList<CourseDetail> courses = new ArrayList<>();
        for (Course course :
                allCourseByTeacherId) {
            int cid = course.getId();
            List<Student> studentsByCid = courseDao.findStudentsByCid(cid);
            int studentCount = studentsByCid.size();
            int totalSignCount = signInDao.findCountByCid(cid);
            int signedCount = signInDao.findSignedCountByCid(cid);
      /*  System.out.println("=============");
        System.out.println(totalSignCount);
        System.out.println(signedCount);
        System.out.println(signedCount / (double) (totalSignCount*studentCount));*/
            double signRate = ((int) ((signedCount / (double) (totalSignCount * studentCount)) * 10000)) / 100.0;
//            System.out.println(signRate);
            int totalDiscussionCount = discussionDao.findByCourseId(cid).size();
            int discussedCount = discussionDao.findDiscussedCountByCid(cid);
            double discussionRate = ((int) ((discussedCount / (double) (totalDiscussionCount * studentCount)) * 10000)) / 100.0;
            CourseDetail courseDetail = new CourseDetail(course.getName(), course.getHour(), course.getInvite_code(), totalSignCount, signRate, totalDiscussionCount, discussionRate, studentCount, cid);
            courses.add(courseDetail);
        }
        request.getSession().setAttribute("courses", courses);
        return "forward:/chooseCourse.jsp";
    }

    @RequestMapping("/findTeacherByName")
    public String findTeacherById(HttpServletRequest request, String name) {
        List<Teacher> list = dao.findTeacherByName("%" + name + "%");
        if (list.size() == 0) {
            list = null;
        }
        request.setAttribute("teachers", list);
        return "forward:/chooseTeacher.jsp";
    }

    @RequestMapping("/findCourseByName")
    public String findCourseByName(HttpServletRequest request, String name, int tid) {
        List<Course> list = courseDao.findCourseByName("%" + name + "%", tid);

        ArrayList<CourseDetail> courses = new ArrayList<>();
        if (list.size() == 0) {
            courses = null;
        } else {
            for (Course course :
                    list) {
                int cid = course.getId();
                List<Student> studentsByCid = courseDao.findStudentsByCid(cid);
                int studentCount = studentsByCid.size();
                int totalSignCount = signInDao.findCountByCid(cid);
                int signedCount = signInDao.findSignedCountByCid(cid);
      /*  System.out.println("=============");
        System.out.println(totalSignCount);
        System.out.println(signedCount);
        System.out.println(signedCount / (double) (totalSignCount*studentCount));*/
                double signRate = ((int) ((signedCount / (double) (totalSignCount * studentCount)) * 10000)) / 100.0;
//            System.out.println(signRate);
                int totalDiscussionCount = discussionDao.findByCourseId(cid).size();
                int discussedCount = discussionDao.findDiscussedCountByCid(cid);
                double discussionRate = ((int) ((discussedCount / (double) (totalDiscussionCount * studentCount)) * 10000)) / 100.0;
                CourseDetail courseDetail = new CourseDetail(course.getName(), course.getHour(), course.getInvite_code(), totalSignCount, signRate, totalDiscussionCount, discussionRate, studentCount, cid);
                courses.add(courseDetail);
            }
        }
        request.getSession().setAttribute("courses", courses);
        return "forward:/chooseCourse.jsp";
    }

 /*   @RequestMapping("/findCourseDetail")
    public String findCourseDetail(HttpServletRequest request, int id) {


    }*/

    @RequestMapping(value = "/exportCourseDetail", method = RequestMethod.POST)
    @ResponseBody
    public String exportCourseDetail(HttpServletRequest request) {

        /** 构建文件保存的目录* */
        String realPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println("========realPath=======");
        System.out.println(realPath);
        String webPath = "/static/excel";
        String localPath = realPath + webPath;
        String name = "course_detail.xlsx";
        ArrayList<CourseDetail> courses = (ArrayList<CourseDetail>) request.getSession().getAttribute("courses");
        ExcelUtils.genCourseDetailExcel(courses, localPath, name);
        return webPath + "/" + name;
    }

    @RequestMapping(value = "/findLineData")
    @ResponseBody
    public String findLineData(int cid) {
        int duration = 30*12;
        /*
        获取最近的签到数据
        * */
//      计算查询签到的最早时间
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_YEAR, -duration);
        Date _earlyDay = instance.getTime();
        Timestamp earlyTime = new Timestamp(_earlyDay.getTime());
//      获取在指定时间区间内的签到
        List<SignIn> allSigns = signInDao.findAllSignByCourseId(cid);
        ArrayList<SignIn> requiredSignIns = new ArrayList<>();

        for (SignIn sign :
                allSigns) {
            if (sign.getDeadline().after(earlyTime)) {
                requiredSignIns.add(sign);
            }
        }
//        按照日期排序
        Comparator c = (Comparator<SignIn>) (o1, o2) -> {

            if (o1.getDeadline().before(o2.getDeadline()))
                return -1;
                //注意！！返回值必须是一对相反数，否则无效。jdk1.7以后就是这样。
                //		else return 0; //无效
            else return 1;
        };
        requiredSignIns.sort(c);
//        查找每次签到的签到人数并记录签到的日期
        ArrayList<Integer> count = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
        for (SignIn sign :
                requiredSignIns) {
            int id = sign.getId();
            int signedStudentsCount = signInDao.findSignedStudentsById(id).size();
            count.add(signedStudentsCount);
            Timestamp deadline = sign.getDeadline();
            Date date1 = new Date(deadline.getTime());
            String time = sdf.format(date1);
            times.add(time);
        }
//        封装折线图所需数据
//        JsonData data = new JsonData("课程签到人数统计", count);
//        假数据，用于测试效果
        ArrayList<Integer> fakeData = new ArrayList<>();
        fakeData.add(69);
        fakeData.add(77);
        fakeData.add(70);
        fakeData.add(79);
        fakeData.add(60);
        JsonData data = new JsonData("课程签到人数统计", fakeData);
        LineData lineData = new LineData(times, data);
        /*response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");*/
        String s = JSONObject.toJSONString(lineData);
        return s;
    }

    @RequestMapping("/showSignDetail")
    public String showSignDetail(int cid, HttpServletRequest request) {
        request.setAttribute("cid", cid);
        Course course = courseDao.findCourseById(cid).get(0);

        List<Student> studentsByCid = courseDao.findStudentsByCid(cid);
        int studentCount = studentsByCid.size();
        int totalSignCount = signInDao.findCountByCid(cid);
        int signedCount = signInDao.findSignedCountByCid(cid);
      /*  System.out.println("=============");
        System.out.println(totalSignCount);
        System.out.println(signedCount);
        System.out.println(signedCount / (double) (totalSignCount*studentCount));*/
        double signRate = ((int) ((signedCount / (double) (totalSignCount * studentCount)) * 10000)) / 100.0;
//            System.out.println(signRate);
        int totalDiscussionCount = discussionDao.findByCourseId(cid).size();
        int discussedCount = discussionDao.findDiscussedCountByCid(cid);
        double discussionRate = ((int) ((discussedCount / (double) (totalDiscussionCount * studentCount)) * 10000)) / 100.0;
        CourseDetail courseDetail = new CourseDetail(course.getName(), course.getHour(), course.getInvite_code(), totalSignCount, signRate, totalDiscussionCount, discussionRate, studentCount, cid);
        request.getSession().setAttribute("course", courseDetail);
        return "forward:/signDetail.jsp";
    }

    @ResponseBody
    @RequestMapping("/checkConnection")
    public String isConnectionOk() {
        return "ok";
    }
}
