package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.*;
import cn.zyity.attendancemanage.dao.IAttendanceDao;
import cn.zyity.attendancemanage.dao.ISignInDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    IAttendanceDao attendanceDao;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpServletResponse resp, HttpServletRequest request) {
        try {

            /*===========获取对象==============*/
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            Attendance attendance = (Attendance) ois.readObject();
            System.out.println("attendance=" + attendance);
            attendanceDao.add(attendance);
            System.out.println("id=" + attendance.getId());
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串

            o.put("id", attendance.getId());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/findSignedItem")
    public void findSignedItem(HttpServletResponse resp, int id) {
        try {

            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            List<AttendanceItem> signedItem = attendanceDao.findSignedItem(id);
            o.put("data", JSONArray.fromObject(signedItem).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findById")
    public void findById(HttpServletResponse resp, int id) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            List<Attendance> attendance = attendanceDao.findById(id);
            String data = com.alibaba.fastjson.JSONArray.toJSONString(attendance);
            o.put("data", data);
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findCompletedStudentById")
    public void findCompletedStudentById(int id, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            List<Student> students = attendanceDao.findCompletedStudentsById(id);
            System.out.println("=======completeAttendanceStudents====");
            System.out.println(students);
            o.put("data", JSONArray.fromObject(students).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findAllByStudentId")
    public void findAllStudentsByAttendanceId(int id, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            System.out.println("=======allAttendanceOfStudent====");
            List<Attendance> allByStudentId = attendanceDao.findAllByStudentId(id);
            String s = com.alibaba.fastjson.JSONArray.toJSONString(allByStudentId);
            o.put("data", s);
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Autowired
    ISignInDao signInDao;
    @RequestMapping("/findAllStudentAttendanceData")
    public void findAllStudentAttendanceData(int sid, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");

            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
//        对象转字符串
            HashMap<String, ArrayList> map = new HashMap<>();
            List<Attendance> allByStudentId = attendanceDao.findAllByStudentId(sid);
            map.put("allAttendances", (ArrayList) allByStudentId);
            List<SignIn> allSignByStudentId = signInDao.findAllSignByStudentId(sid);
            map.put("allSigns", (ArrayList) allSignByStudentId);
            List<SignIn> allSigns = signInDao.findAllSignByStudentId(sid);
            List<Integer> signedIds = signInDao.findCompletedSignByStudentId(sid);
            ArrayList<Boolean> status = new ArrayList<Boolean>();
            for (SignIn sign :
                    allSigns) {
                if (signedIds.contains(sign.getId()))
                    status.add(true);
                else
                    status.add(false);
            }
            map.put("isSignCompleted", status);
            List<Attendance> attendanceList = attendanceDao.findAllByStudentId(sid);
            List<Integer> integers = attendanceDao.findCompletedAttendanceByStudentId(sid);
            ArrayList<Boolean> attendanceStatus = new ArrayList<Boolean>();
            for (Attendance attendance :
                    attendanceList) {
                if (integers.contains(attendance.getId()))
                    attendanceStatus.add(true);
                else
                    attendanceStatus.add(false);
            }
            map.put("isAttendanceCompleted", attendanceStatus);
            String s = com.alibaba.fastjson.JSON.toJSONString(map);
            /*System.out.println("=======findAllStudentAttendanceData sid = "+sid);
            System.out.println(s);*/

            o.put("data", s);
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findCompleteStatusById")
    public void findCompleteStatusById(int id, HttpServletResponse resp) {
        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            List<Attendance> attendanceList = attendanceDao.findAllByStudentId(id);
            List<Integer> integers = attendanceDao.findCompletedAttendanceByStudentId(id);
            ArrayList<Boolean> attendanceStatus = new ArrayList<Boolean>();
            for (Attendance attendance :
                    attendanceList) {
                if (integers.contains(attendance.getId()))
                    attendanceStatus.add(true);
                else
                    attendanceStatus.add(false);
            }
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            System.out.println("=======attendance completed attendanceStatus====");
            System.out.println(attendanceStatus);
            o.put("data", JSONArray.fromObject(attendanceStatus).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    通过id查询学生
    * */
    @RequestMapping("/findStudentsByAttendanceId")
    public void findAllStudentsByAid(int id, HttpServletResponse resp) {
        try {
            List<Student> students = attendanceDao.findAllStudentsByAttendanceId(id);

            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            JSONObject o = new JSONObject();
//        对象转字符串
            System.out.println("=======findStudentsByAttendanceId====");
            System.out.println(students);
            o.put("data", JSONArray.fromObject(students).toString());
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/findAttendanceByAidAndSid")
    public void findIsAttended(int aid, int sid, HttpServletResponse resp) {
        List<StudentAttendanceMap> list = attendanceDao.findAttendanceBySidAndAid(sid, aid);

        try {
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            //        对象转字符串
            System.out.println("=======list====");
            System.out.println(list);
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(list));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/addAttendanceMap")
    public void addAttendanceMap(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            StudentAttendanceMap map = (StudentAttendanceMap) ois.readObject();
            attendanceDao.addAttendanceMap(map);
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findByTeacherId")
    public void findByTid(int id, HttpServletResponse resp) {

        try {
            List<Attendance> byId = attendanceDao.findByTeacherId(id);
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

    @RequestMapping("/findAllTeacherAttendanceData")
    public void findAllTeacherAttendanceData(int id, HttpServletResponse resp) {

        try {
            HashMap<String, List> map = new HashMap<>();
            List<SignIn> byId = signInDao.findByTeacherId(id);
            map.put("allSigns", byId);
            List<Attendance> attendanceList = attendanceDao.findByTeacherId(id);
            map.put("allAttendances", attendanceList);

            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            //        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONObject.toJSONString(map));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
