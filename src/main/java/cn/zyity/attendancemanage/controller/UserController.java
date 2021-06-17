package cn.zyity.attendancemanage.controller;

import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.bean.Teacher;
import cn.zyity.attendancemanage.dao.IUserDao;
import cn.zyity.attendancemanage.util.ExcelUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserDao dao;

    @RequestMapping("/findTeacherById")
    public void findTeacherById(HttpServletResponse resp, int id) {

        try {
            List<Teacher> teacher = dao.findTeacherById(id);
            /*===========获取对象==============*/

            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            String data = com.alibaba.fastjson.JSONArray.toJSONString(teacher);
            /*JSONObject jsonTeacher = JSONObject.fromObject(teacher);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", jsonTeacher.toString());*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", data);
            writer.write(jsonObject.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/showStudentInfo")
    public String showStudentInfo(HttpServletRequest request) {
        request.setAttribute("showInfo",true);
        return "forward:/user/findAllStudents";
    }
    @RequestMapping("/showTeacherInfo")
    public String showTeacherInfo(HttpServletRequest request) {
        request.setAttribute("showInfo",true);
        return "forward:/user/findAllTeachers";
    }
        @RequestMapping("/editStudentInfo")
    public String editStudentInfo(HttpServletRequest request) {
        request.setAttribute("edit",true);
        return "forward:/user/findAllStudents";
    }
    @RequestMapping("/editTeacherInfo")
    public String editTeacherInfo(HttpServletRequest request) {
        request.setAttribute("edit",true);
        return "forward:/user/findAllTeachers";
    }
        @RequestMapping("/deleteStudentInfo")
    public String deleteStudentInfo(HttpServletRequest request) {
        request.setAttribute("delete",true);
        return "forward:/user/findAllStudents";
    }
    @RequestMapping("/deleteTeacherInfo")
    public String deleteTeacherInfo(HttpServletRequest request) {
        request.setAttribute("delete",true);
        return "forward:/user/findAllTeachers";
    }

    @RequestMapping("/findStudentById")
    public void findStudentById(HttpServletResponse resp, int id) {

        try {
            List<Student> s = dao.findStudentById(id);
            /*===========获取对象==============*/

            /*---------------向客户端回传结果----------------*/
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            PrintWriter writer = null;
            writer = resp.getWriter();
            String data = com.alibaba.fastjson.JSONArray.toJSONString(s);
            /*JSONObject jsonTeacher = JSONObject.fromObject(s);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", jsonTeacher.toString());*/
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", data);
            writer.write(jsonObject.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/uploadAttendaceImg", method = RequestMethod.POST)
    public void uploadOrderSignImage(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        try {
            System.out.println("======load img========");
            MultipartHttpServletRequest rq = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> file_list = rq.getFileMap();

            if (file_list != null && file_list.size() > 0) {
                if (file_list.containsKey("inputName")) {
                    MultipartFile file = file_list.get("inputName");
                    if (file != null) {
                        String fileName = file.getOriginalFilename();
                        String newFileName = "";
//                        String[] desp = fileName.split("\\.");
//                        if (desp != null && desp.length > 0) {
//                            String extendName = desp[desp.length - 1];
//                            newFileName = new Date().getTime() + "." + extendName;
                        ServletContext servletContext = request.getSession().getServletContext();
                        String uploadPath = servletContext.getRealPath("/") + "static/upload";
                        File saveFile = new File(uploadPath, fileName);
                        file.transferTo(saveFile);
                        System.out.println(saveFile);
//                        }
                        session.setAttribute("newfilename", newFileName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteStudentById")
    public String deleteStudent(int id) {
        dao.deleteStudent(id);
        return "forward:/user/findAllStudents";
    }

    @RequestMapping("/deleteTeacherById")
    public String deleteTeacher(int id) {
        dao.deleteTeacher(id);
        return
                "forward:/user/findAllTeachers";
    }

    @RequestMapping("/findAllStudents")

    public String findStudents(HttpServletRequest request) {

        List<Student> allStudents = dao.findAllStudents();
        request.setAttribute("students", allStudents);
        return "forward:/student_list.jsp";
    }

    @RequestMapping("/findAllTeachers")

    public String findTeachers(HttpServletRequest request) {
        List<Teacher> allTeachers = dao.findAllTeachers();
        request.setAttribute("teachers", allTeachers);
        return "forward:/teacher_list.jsp";
    }

    @RequestMapping("/editStudent")
    public String updateStu(Student student, HttpServletRequest request) {
        request.setAttribute("student", student);
        return "forward:/update_student.jsp";
    }

    @RequestMapping("/editTeacher")
    public String updateTea(Teacher teacher, HttpServletRequest request) {
        request.setAttribute("teacher", teacher);
        return "forward:/update_teacher.jsp";
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public String updateS(Student student, HttpServletRequest request) {
        dao.updateStudent(student);
        return "forward:/user/findAllStudents";
    }

    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public String updateT(Teacher teacher) {
        dao.updateTeacher(teacher);
        return "forward:/user/findAllTeachers";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(Student student) {
        dao.addStudent(student);
        return "forward:/user/findAllStudents";
    }

    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public String addTeacher(Teacher teacher) {
        dao.addTeacher(teacher);
        return "forward:/user/findAllTeachers";
    }

    @RequestMapping(value = "/uploadExcel", method = {RequestMethod.POST})
    public String importData(MultipartHttpServletRequest request, HttpServletResponse response, HttpServletRequest request2) {
        int id = (Integer) request.getSession().getAttribute("id");
        try {
            Iterator<String> itr = request.getFileNames();
            String fileName = "";

            if (itr.hasNext()) {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
                /** 构建文件保存的目录* */
                String path = getClass().getClassLoader().getResource("/").getPath() + "excel/";
//                        + dateformat.format(new Date());
                /** 得到文件保存目录的真实路径* */
                String logoRealPathDir = request.getSession().getServletContext()
                        .getRealPath("/");
                System.out.println("======servletContext.getRealPath=" + logoRealPathDir);
                /** 根据真实路径创建目录* */
                File savePath = new File(path);
                if (!savePath.exists())
                    savePath.mkdirs();
                /** 页面控件的文件流* */
                MultipartFile mpf = multipartRequest.getFile("file");
                /** 获取文件的后缀* */
                String suffix = mpf.getOriginalFilename().substring(
                        mpf.getOriginalFilename().lastIndexOf("."));
                /** 使用UUID生成文件名称* */
                /** 拼成完整的文件保存路径加文件* */
                if (id == 1) {
                    fileName = "students" + suffix;// 构建文件名称
                } else {
                    fileName = "teachers" + suffix;
                }
                fileName = path + fileName;
                System.out.println("upload-》文件保存全路径" + fileName);
                File file = new File(fileName);
                try {
                    FileCopyUtils.copy(mpf.getBytes(), file);
                    System.out.println("上传成功！");
                } catch (IOException e) {
                    System.out.println("上传失败！");
                    e.printStackTrace();
                }
                /*System.out.println("2.4 add to files");
                files.add(fileMeta);*/

//                System.out.println("success uploaded=" + files.size());
            }
//            return files;


           /* String sPath =  getClass().getClassLoader().getResource("/").getPath()+"excel/students.xlsx";
            String tPath =  getClass().getClassLoader().getResource("/").getPath()+"excel/teachers.xlsx";
            System.out.println("=================");
            System.out.println(tPath);*/
            if (id == 1) {
                System.out.println("students==========");
                ArrayList<Student> students = ExcelUtils.getStudentsFromExcel(fileName);
                System.out.println(students);
                for (Student s :
                        students) {
                    dao.addStudent(s);
                }
            } else {
                System.out.println("teachers==========");

                ArrayList<Teacher> teachers = ExcelUtils.getTeachersFromExcel(fileName);
                System.out.println(teachers);
                for (Teacher t :
                        teachers) {
                    dao.addTeacher(t);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward:/manageIndex.jsp";
    }

    @RequestMapping("/importData")
    public String importData(HttpServletRequest request, int id) {
        request.getSession().setAttribute("id", id);
        return "forward:/upload_excel.jsp";
    }

    @RequestMapping("/deleteSelectedStudents")
    public String deleteSelectedStudents(HttpServletRequest request) {
        String[] lids = request.getParameterValues("lid");
        for (String id :
                lids) {
            int i = Integer.parseInt(id);
            dao.deleteStudent(i);
        }
        return "forward:/user/findAllStudents";
    }

    @RequestMapping("/deleteSelectedTeachers")
    public String deleteSelectedTeachers(HttpServletRequest request) {
        String[] lids = request.getParameterValues("lid");
        for (String id :
                lids) {
            int i = Integer.parseInt(id);
            dao.deleteTeacher(i);
        }
        return "forward:/user/findAllTeachers";
    }

    @RequestMapping("/updateTeacherPsd")
    public void updateTeacherPsd(int id, String psd) {
        dao.updateTeacherPsd(id, psd);
    }
    @RequestMapping("/updateStudentPsd")
    public void updateStudentPsd(int id, String psd) {
        dao.updateStudentPsd(id, psd);
    }

    @RequestMapping("/updateStudentHead")
    public void updateStudentHead(int id, String imgUrl) {
        dao.updateStudentImg(id,imgUrl);
    }
    @RequestMapping("/updateTeacherHead")
    public void updateTeacherHead(int id, String imgUrl) {

        dao.updateTeacherImg(id,imgUrl);
    }

    @RequestMapping("/findTeacherByName")
    public String findTeacherByName(String name, HttpServletRequest request) {
        List<Teacher> teachers = dao.findTeacherByName("%"+name+"%");
        if (teachers.size() == 0) {
            teachers = null;
        }
        request.setAttribute("teachers",teachers);
        return "forward:/teacher_list.jsp";
    }
    @RequestMapping("/findStudentByName")
    public String findStudentByName(String name, HttpServletRequest request) {
        List<Student> students = dao.findStudentByName("%"+name+"%");
        if (students.size() == 0) {
            students = null;
        }
        request.setAttribute("students",students);
        return "forward:/student_list.jsp";
    }
}
