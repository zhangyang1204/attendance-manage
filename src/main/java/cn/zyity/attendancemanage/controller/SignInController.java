package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.*;
import cn.zyity.attendancemanage.dao.ICourseDao;
import cn.zyity.attendancemanage.dao.ICourseMessageDao;
import cn.zyity.attendancemanage.dao.ISignInDao;
import cn.zyity.attendancemanage.service.SignInService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sign")
public class SignInController {
    @Autowired
    SignInService service;

    @Autowired
    ISignInDao dao;

    @Autowired
    ICourseMessageDao courseMessageDao;

    @Autowired
    ICourseDao courseDao;

    @RequestMapping("/add")

    public void addSignIn(HttpServletRequest request) {
        try {

            /*===========获取对象==============*/
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            SignIn sign = (SignIn) ois.readObject();
            System.out.println("=======add Sign======");
            System.out.println(sign);
            dao.add(sign);

               /*
            ====添加到课程消息表中
            * */
            int signId = sign.getId();
            int courseId = sign.getCourse_id();
            int owner_id = courseDao.findTeacherIdsByCourseId(courseId).get(0);
            String owner_role = "老师";
            int type = MessageType.MESSAGE_TYPE_SIGN;
            CourseMessage courseMessage = new CourseMessage(courseId, signId, owner_role, type, owner_id, "");
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

    @RequestMapping("/findAllByStudentId")
    public void findAllByStuentId(int id, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            System.out.println("=======allAttendanceOfStudent====");
            List<SignIn> allSignByStudentId = dao.findAllSignByStudentId(id);
            System.out.println(allSignByStudentId);
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(allSignByStudentId));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findAllSignByCourseId")
    public void findAllSignByCourseId(int cid, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            System.out.println("=======allAttendanceOfStudent====");
            List<SignIn> signs = dao.findAllSignByCourseId(cid);
//            System.out.println(signs);
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(signs));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findCompleteStatus")
    public void findCompleteStatus(int cid,int sid, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            List<SignIn> allSigns = dao.findAllSignByCourseId(cid);
            List<Integer> signedIds = dao.findCompletedSignByStudentId(sid);
            ArrayList<Boolean> status = new ArrayList<Boolean>();
            for (SignIn sign :
                    allSigns) {
                if (signedIds.contains(sign.getId()))
                    status.add(true);
                else
                    status.add(false);
            }
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            System.out.println("=======status====");
            System.out.println(status);
            o.put("data", JSONArray.fromObject(status).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/findSignCompleteStatusByStudentId")
    public void findSignCompleteStatusByStudentId(int id, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            List<SignIn> allSigns = dao.findAllSignByStudentId(id);
            List<Integer> signedIds = dao.findCompletedSignByStudentId(id);
            ArrayList<Boolean> status = new ArrayList<Boolean>();
            for (SignIn sign :
                    allSigns) {
                if (signedIds.contains(sign.getId()))
                    status.add(true);
                else
                    status.add(false);
            }
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            System.out.println("=======status====");
            System.out.println(status);
            o.put("data", JSONArray.fromObject(status).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findStudentsBySignId")
    public void findStudentsBySignId(int id, HttpServletResponse resp) {
        try {

            List<Student> students = dao.findAllStudentsById(id);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            System.out.println("=======findStudentsBySignId====");
            System.out.println(students);
            o.put("data", JSONArray.fromObject(students).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("findCompletedStudentById")
    public void findCompletedStudentById(int id, HttpServletResponse resp) {
        try {

            List<Student> students = dao.findSignedStudentsById(id);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            System.out.println("=======findCompletedStudentById====");
            System.out.println(students);
            o.put("data", JSONArray.fromObject(students).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/findById")
    public void findById(int id, HttpServletResponse resp) {
        try {
            List<SignIn> signs = dao.findById(id);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            String data = com.alibaba.fastjson.JSONArray.toJSONString(signs);
            /*JSONObject jsonTeacher = JSONObject.fromObject(teacher);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", jsonTeacher.toString());*/
            com.alibaba.fastjson.JSONObject jsonObject = new com.alibaba.fastjson.JSONObject();
            jsonObject.put("data", data);
            writer.write(jsonObject.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findCourse")
    public void findCourseBySignId(int id, HttpServletResponse resp) {
        try {
            Course course = dao.findCourseBySignId(id).get(0);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
            //        对象转字符串
            System.out.println("=======course====");
            System.out.println(course);
            o.put("data", JSONArray.fromObject(course).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findSignMap")
    public void findSignMap(int studentId, int signId, HttpServletResponse resp) {
        List<StudentSignMap> signMap = dao.findSignMap(signId, studentId);
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            String data = com.alibaba.fastjson.JSONArray.toJSONString(signMap);
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            o.put("data", data);
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findSignCount")
    public void findSignCountOfCourse(int id, HttpServletResponse resp) {
        int countByCid = dao.findCountByCid(id);
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
            //        对象转字符串
            System.out.println("=======flag====");
            System.out.println(countByCid);
            o.put("data", JSONArray.fromObject(countByCid).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findSignedCountOfCourse")
    public void findSignedCountOfCourse(int id, HttpServletResponse resp) {
        int countByCid = dao.findSignedCountByCid(id);
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
            //        对象转字符串
            System.out.println("=======flag====");
            System.out.println(countByCid);
            o.put("data", JSONArray.fromObject(countByCid).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/addSignMap")
    public void addSignMap(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            StudentSignMap map = (StudentSignMap) ois.readObject();
            dao.addSignMap(map);
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findByTeacherId")
    public void findByTid(int id, HttpServletResponse resp) {

        try {
            List<SignIn> byId = dao.findByTeacherId(id);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            //        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(byId));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
