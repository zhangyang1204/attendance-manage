package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.MyMessage;
import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.dao.IMessageDao;
import cn.zyity.attendancemanage.dao.IUserDao;
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
@RequestMapping("/message")
public class MessageController {
    @Autowired
    IMessageDao dao;

    @Autowired
    IUserDao userDao;

    @RequestMapping("/deleteTMsgByIds")

    public void deleteTMsgByIds(HttpServletRequest request) {
        try {

            ServletInputStream inputStream = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ArrayList<Integer> ids = (ArrayList<Integer>) ois.readObject();
            int tid = ids.remove(ids.size() - 1);

            System.out.println("============="+ids);
            for (int id :
                    ids) {
                dao.deleteTMsg(tid, id);
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteSMsgByIds")

    public void deleteSMsgByIds(HttpServletRequest request) {
        try {

            ServletInputStream inputStream = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            ArrayList<Integer> ids = (ArrayList<Integer>) ois.readObject();
            int sid = ids.remove(ids.size() - 1);

            for (int id :
                    ids) {
                dao.deleteSMsg(sid, id);
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addMessage")
    public void addMessage(HttpServletRequest request) {
        try {
//            System.out.println("======tid======="+tid);
            ServletInputStream inputStream = request.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            MyMessage message = (MyMessage) ois.readObject();
            dao.add(message);
            dao.addTMap(message.getId(), message.getTid());
            List<Student> students = userDao.findStudentsByMajorAndClazz(message.getMajor(), message.getClazz());
            for (Student s :
                    students) {
                dao.addSMap(message.getId(), s.getId());
            }
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findMessagesByTid")
    public void findMessageByTid(int id, HttpServletResponse resp) {
        try {
            List<Integer> list = dao.findByTid(id);
            ArrayList<MyMessage> messages = new ArrayList<MyMessage>();
            for (int i :
                    list) {
                List<MyMessage> byId = dao.findById(i);
                messages.add(byId.get(0));
            }
            System.out.println("==========findMessagesByTid=========");
            System.out.println(list);
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            //        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(messages));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/findMessagesBySid")
    public void findMessagesByMajorAndClazz(int id, HttpServletResponse resp) {
        try {
            List<Integer> list = dao.findBySid(id);
//            System.out.println("==========findMessagesBySid=========");
            ArrayList<MyMessage> messages = new ArrayList<MyMessage>();
            for (int i :
                    list) {
                List<MyMessage> byId = dao.findById(i);
                messages.add(byId.get(0));
            }
            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = resp.getWriter();
            com.alibaba.fastjson.JSONObject o = new com.alibaba.fastjson.JSONObject();
            //        对象转字符串
            o.put("data", com.alibaba.fastjson.JSONArray.toJSONString(messages));
            writer.write(o.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
