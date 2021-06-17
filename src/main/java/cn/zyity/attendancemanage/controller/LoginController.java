package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.Admin;
import cn.zyity.attendancemanage.bean.LoginBean;
import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.bean.Teacher;
import cn.zyity.attendancemanage.dao.IUserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@Controller()
@RequestMapping("/login")
public class LoginController {
    @Autowired
    IUserDao userDao;

    @RequestMapping("/customLogin")
    public void login(String account, String password, HttpServletResponse resp) {
        List<Teacher> teacher = null;
        LoginBean bean;
        List<Student> studentByAccountAndPsd = userDao.findStudentByAccountAndPsd(account, password);
        if (studentByAccountAndPsd != null && studentByAccountAndPsd.size() > 0) {
            bean = new LoginBean(true, "student", studentByAccountAndPsd.get(0).getId());
        } else if ((teacher = userDao.findTeacherByAccountAndPsd(account, password)) != null && teacher.size() > 0) {
            bean = new LoginBean(true, "teacher", teacher.get(0).getId());
        } else {
            bean = new LoginBean(false, null, 0);
        }
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
        String jsonCourses = JSONArray.fromObject(bean).toString();
        o.put("data", jsonCourses);
        writer.write(o.toString());
        writer.close();

    }

    @RequestMapping("/webLogin")
    public void webLogin(String account, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!verifycode.equalsIgnoreCase(checkcode_server)) {
            request.setAttribute("login_msg", "验证码输入错误！请重试");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        List<Admin> loginUser = userDao.findAdmin(account, password);
        if (loginUser == null||loginUser.size()==0) {
            request.setAttribute("login_msg", "账号或密码错误");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("loginId", loginUser.get(0).getId());
            response.sendRedirect(request.getContextPath() + "/main.html");
        }
    }

    @RequestMapping("/checkCode")
    public void checkcode(HttpServletRequest request, HttpServletResponse response) {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 90;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0, 0, width, height);

        //产生4个随机验证码
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 4; i++) {
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        String checkCode = sb.toString();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的大小
        g.setFont(new Font("黑体", Font.BOLD, 24));
        //向图片上写入验证码
        g.drawString(checkCode, 15, 25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(image, "PNG", outputStream);

        } catch (IOException e) {
            System.out.println("出异常了");
        }
    }
}
