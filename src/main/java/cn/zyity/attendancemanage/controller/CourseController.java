package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.*;
import cn.zyity.attendancemanage.dao.ICourseDao;
import cn.zyity.attendancemanage.dao.ICourseMessageDao;
import cn.zyity.attendancemanage.dao.IUserDao;
import cn.zyity.attendancemanage.service.CourseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService service;
    @Autowired
    ICourseDao courseDao;
    @Autowired
    ICourseMessageDao courseMessageDao;
    @Autowired
    IUserDao userDao;

    @RequestMapping("/addCourse")
    public void addCourse(Course course) {
        System.out.println("----------------------");
        System.out.println(course);
        service.addCourse(course);
    }

    @RequestMapping("/findAllCourse")
    public void findAllCourse(HttpServletResponse resp) {
        List<Course> res = service.findAllCourse();
        System.out.println(res);
        /*---------------向客户端回传结果----------------*/
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject o = new JSONObject();
//        对象转字符串
        String jsonCourses = JSONArray.fromObject(res).toString();
        o.put("data", jsonCourses);
//        字符串转对象
//        Course[] courseList = (Course[]) JSONArray.toArray(JSONArray.fromObject(jsonCourses), Course.class);
        writer.write(o.toString());
        writer.close();
        /*---------------向客户端回传结果----------------*/
    }

    @RequestMapping("/findAllCourseByTeacherId")
    public void findAllCourseByTeacherId(int id, HttpServletResponse resp) {
        List<Course> res = service.findAllCourseByTeacherId(id);
        /*---------------向客户端回传结果----------------*/
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject o = new JSONObject();
//        对象转字符串
        String jsonCourses = JSONArray.fromObject(res).toString();
        o.put("data", jsonCourses);
        writer.write(o.toString());
        writer.close();
        /*---------------向客户端回传结果----------------*/
    }

    @RequestMapping("/findAllCourseByStudentId")
    public void findAllCourseByStudentId(int id, HttpServletResponse resp) {
        List<Course> res = service.findAllCourseByStudentId(id);
        /*---------------向客户端回传结果----------------*/
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject o = new JSONObject();
//        对象转字符串
        String jsonCourses = JSONArray.fromObject(res).toString();
        o.put("data", jsonCourses);
        writer.write(o.toString());
        writer.close();
        /*---------------向客户端回传结果----------------*/
    }

    @RequestMapping(value = "/deleteByIds", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteByIds(HttpServletResponse resp, HttpServletRequest request) {
        try {

            /*===========获取对象==============*/
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            ArrayList<Integer> ids = null;
            ids = (ArrayList<Integer>) ois.readObject();

            System.out.println(ids);
            for (int id :
                    ids) {
                courseDao.delete(id);
            }

            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串

            o.put("status", "success");
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public void add(HttpServletResponse resp, HttpServletRequest request) {
        System.out.println("===========");
        try {

            /*===========获取对象==============*/
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            Course course = (Course) ois.readObject();
            courseDao.add(course);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            o.put("status", "success");
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        通过课程id查找该课程中的所有消息
     * */
    @RequestMapping("/findMessagesByCourseId")
    public void findMessages(int cid, HttpServletResponse resp) {
        List<CourseMessage> all = courseDao.findMessagesByCourseId(cid);
        ArrayList<Message> messages = new ArrayList<Message>();
        for (CourseMessage cMessage :
                all) {
            Message message = new Message();
            message.setMessageId(cMessage.getMessage_id());
            message.setContent(cMessage.getContent());
            message.setType(cMessage.getType());
            message.setOwnerId(cMessage.getOwner_id());
            message.setOwnerRole(cMessage.getOwner_role());
            String ownerName;
            String imgUrl;
            if (cMessage.getOwner_role().equals("学生")) {
                Student student = userDao.findStudentById(cMessage.getOwner_id()).get(0);
                imgUrl = student.getHeadimg_url();
                ownerName = student.getName();
            } else {
                Teacher teacher = userDao.findTeacherById(cMessage.getOwner_id()).get(0);
                imgUrl = teacher.getImg_url();
                ownerName = teacher.getName();
            }
            message.setOwnerImgUrl(imgUrl);
            message.setOwnerName(ownerName);
            messages.add(message);
        }
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            o.put("data", messages);
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/findStudentsById")

    public void findStudentsById(int id, HttpServletResponse resp) {
        List<Student> byId = courseDao.findStudentsByCid(id);
        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            JSONArray jsonArray = JSONArray.fromObject(byId);
            o.put("data", jsonArray.toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/findTeacherById")

    public void findTeacherById(int id, HttpServletResponse resp) {
        List<Teacher> byId = courseDao.findTeacherByCid(id);
        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            JSONArray jsonArray = JSONArray.fromObject(byId);
            o.put("data", jsonArray.toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/deleteStudent")
    public void deleteStudent(int cid, int sid) {

        courseDao.deleteStudentOfCourse(cid, sid);
    }

    @RequestMapping("/findByInviteCode")
    public void findByInviteCode(String inviteCode, HttpServletResponse resp) {
        List<Course> course = courseDao.findCourseByInvitecode(inviteCode);
        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            JSONArray jsonArray = JSONArray.fromObject(course);
            o.put("data", jsonArray.toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/addByInviteCode")
    public void addByInviteCode(String inviteCode, int sid) {
        try {

            List<Course> courses = courseDao.findCourseByInvitecode(inviteCode);
            System.out.println("=====addByInviteCode:courses====");
            System.out.println(courses);
            if (courses == null) {
                return;
            }

            StudentCourseMap map = new StudentCourseMap();
            map.setCid(courses.get(0).getId());
            map.setSid(sid);
            courseDao.addStudentCourseMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/updateMsg", method = {RequestMethod.POST})
    public void updateMsg(HttpServletResponse resp, HttpServletRequest request) {
        System.out.println("===========");
        try {

            /*===========获取对象==============*/
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
           CourseMessage message = (CourseMessage) ois.readObject();
            courseMessageDao.add(message);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            o.put("status", "ok");
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
