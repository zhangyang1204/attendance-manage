package cn.zyity.attendancemanage.util;

import cn.zyity.attendancemanage.bean.CourseDetail;
import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.bean.Teacher;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ExcelUtils {

    public static void createExcel(String path, String name, String[] keys) {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
            System.out.println("===============");
            System.out.println("mkdirs=" + mkdirs);
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(path + "/" + name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
//            创建一行
        XSSFRow row = sheet.createRow(0);
//            创建所有列
        for (int i = 0; i < keys.length; i++) {
            row.createCell(i).setCellValue(keys[i]);
        }
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void appendExcel(String path, String name, ArrayList<String> values) {
        FileOutputStream os = null;
        FileInputStream is = null;
        XSSFWorkbook wb = null;
        try {
            is = new FileInputStream(path + "/" + name);
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);
        int totalRow = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.createRow(totalRow);
        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            row.createCell(i).setCellValue(value);
        }
        try {
            os = new FileOutputStream(path + "/" + name);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    *
        提取所有学生信息
    * */
    public static ArrayList<Student> getStudentsFromExcel(String path) {
        FileInputStream is = null;
        XSSFWorkbook wb = null;
        try {
            is = new FileInputStream(path);
            wb = new XSSFWorkbook(is);

            XSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            XSSFRow headRow = sheet.getRow(0);
            int rowLen = headRow.getLastCellNum();
            ArrayList<Student> students = new ArrayList<Student>();
            for (int i = 1; i <= lastRowNum; i++) {
                Student student = new Student();
                for (int j = 0; j < rowLen; j++) {
                    if (headRow.getCell(j).getStringCellValue().equals("姓名")) {
                        student.setName(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("年龄")) {
                        student.setAge(Integer.parseInt(sheet.getRow(i).getCell(j).getStringCellValue()));
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("性别")) {
                        student.setSex(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("身份证号")) {
                        student.setIdcard_number(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("密码")) {
                        student.setPassword(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("专业")) {
                        student.setMajor(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("班级")) {
                        student.setClazz(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("学号")) {
                        student.setStudent_number(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("头像地址")) {
                        student.setHeadimg_url(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                }
                students.add(student);
            }
            System.out.println("===============");
            System.out.println(students);
            return students;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }/*
    *
        提取所有教师信息
    * */

    public static ArrayList<Teacher> getTeachersFromExcel(String path) {
        FileInputStream is = null;
        XSSFWorkbook wb = null;
        try {
            is = new FileInputStream(path);
            wb = new XSSFWorkbook(is);

            XSSFSheet sheet = wb.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            XSSFRow headRow = sheet.getRow(0);
            int rowLen = headRow.getLastCellNum();
            ArrayList<Teacher> teachers = new ArrayList<Teacher>();
            for (int i = 1; i <= lastRowNum; i++) {
                Teacher teacher = new Teacher();
                for (int j = 0; j < rowLen; j++) {
                    if (headRow.getCell(j).getStringCellValue().equals("姓名")) {
                        teacher.setName(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("年龄")) {
                        teacher.setAge(Integer.parseInt(sheet.getRow(i).getCell(j).getStringCellValue()));
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("性别")) {
                        teacher.setSex(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("身份证号")) {
                        teacher.setIdcard_number(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("密码")) {
                        teacher.setPassword(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                    if (headRow.getCell(j).getStringCellValue().equals("头像地址")) {
                        teacher.setImg_url(sheet.getRow(i).getCell(j).getStringCellValue());
                        continue;
                    }
                }
                teachers.add(teacher);
            }
            System.out.println("============teachers==========");
            System.out.println(teachers);

            return teachers;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void genCourseDetailExcel(ArrayList<CourseDetail> details, String path, String name) {
        File file = new File(path + "/" + name);
        if (file.exists()) {
            file.delete();
        }
        String[] keys = new String[]{"课程名", "课时", "邀请码", "学生人数", "签到次数", "签到率", "讨论次数", "讨论率"};
        createExcel(path, name, keys);
        FileOutputStream os = null;
        FileInputStream is = null;
        XSSFWorkbook wb = null;
        try {
            is = new FileInputStream(path + "/" + name);
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = wb.getSheetAt(0);
        for (int rowNum = 1; rowNum <= details.size(); rowNum++) {
            XSSFRow row = sheet.createRow(rowNum);
            CourseDetail detail = details.get(rowNum - 1);
            ArrayList<String> values = new ArrayList<>();
            values.add(detail.getName());
            values.add(detail.getHour() + "");
            values.add(detail.getInvite_code());
            values.add(detail.getStudentCount() + "");
            values.add(detail.getSignCount() + "");
            values.add(detail.getSignRate() + "%");
            values.add(detail.getDiscussionCount() + "");
            values.add(detail.getDiscussionRate() + "%");
            for (int i = 0; i < values.size(); i++) {
                String value = values.get(i);
                row.createCell(i).setCellValue(value);
            }
        }

        try {
            File file1 = new File(path + "/" + name);
            System.out.println("======file==========");
            System.out.println(file1);
            os = new FileOutputStream(path + "/" + name);
            wb.write(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
