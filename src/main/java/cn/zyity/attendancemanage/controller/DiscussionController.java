package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.*;
import cn.zyity.attendancemanage.dao.ICourseDao;
import cn.zyity.attendancemanage.dao.ICourseMessageDao;
import cn.zyity.attendancemanage.dao.IDiscussionDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/discussion")
public class DiscussionController {

    @Autowired
    IDiscussionDao dao;
    @Autowired
    ICourseMessageDao courseMessageDao;

    @Autowired
    ICourseDao courseDao;

    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse resp) {
        try {

            /*===========获取对象==============*/
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            Discussion discussion = (Discussion) ois.readObject();
            System.out.println("=======add discussion======");
            System.out.println(discussion);
            dao.add(discussion);

            /*
            ====添加到课程消息表中
            * */
            int discussionId = discussion.getId();
            int courseId = discussion.getCourse_id();
            int owner_id = courseDao.findTeacherIdsByCourseId(courseId).get(0);
            String owner_role = "老师";
            int type = MessageType.MESSAGE_TYPE_DISCUSSION;
            CourseMessage courseMessage = new CourseMessage(courseId, discussionId, owner_role, type, owner_id, "");
            courseMessageDao.add(courseMessage);



            /*---------------向客户端回传结果----------------*/
           /* resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串

            o.put("status", "success");
            writer.write(o.toString());
            writer.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findStudentDiscussion")
    public void findStudentDiscussion(int id,int did, HttpServletResponse resp) {
        List<StudentDiscussion> byStudentId = dao.findByStudentId(id,did);

        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            JSONArray jsonArray = JSONArray.fromObject(byStudentId);
            o.put("data", jsonArray.toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/findAllStudentDiscussions")
    public void findAllStudentDiscussions(int id,HttpServletResponse resp) {
        List<StudentDiscussion> byStudentId = dao.findAllStudentDiscussionsById(id);

        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            JSONArray jsonArray = JSONArray.fromObject(byStudentId);
            o.put("data", jsonArray.toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findById")

    public void findById(int id, HttpServletResponse resp) {
        List<Discussion> byId = dao.findById(id);
        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(byId));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @RequestMapping("/findByCourseId")

    public void findByCourseId(int id, HttpServletResponse resp) {
        List<Discussion> byId = dao.findByCourseId(id);
        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(byId));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @RequestMapping("/findByDiscussionId")

    public void findByDiscussionId(int id, HttpServletResponse resp) {
        List<StudentDiscussion> byId = dao.findByDiscussionId(id);
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

    @RequestMapping("/findStudentsByDiscussionId")
    public void findStudentsByDiscussionId(int id, HttpServletResponse resp) {
        List<Student> byId = dao.findStudentsByDiscussionId(id);
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

    @RequestMapping("findCompletedDiscussionStudents")
    public void findCompletedDiscussionStudents(int id, HttpServletResponse resp) {
        /*---------------向客户端回传结果----------------*/
        try {
            List<Student> byId = dao.findCompletedDiscussionsStudents(id);

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
    @RequestMapping("/addDiscussionMap")
    public void addDiscussionMap(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            StudentDiscussionMap map = (StudentDiscussionMap) ois.readObject();
            dao.addStudentDiscussionMap(map);
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteMap")
    public void deleteMap(int id) {
        dao.deleteMap(id);
    }

    @RequestMapping("/findMapId")
    public void findMap(int sid, int did, HttpServletResponse resp) {
        List<Integer> map = dao.findMap(did, sid);
        /*---------------向客户端回传结果----------------*/
        try {

            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(map));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
