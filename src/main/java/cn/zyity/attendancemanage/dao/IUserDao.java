package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.Admin;
import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.bean.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {

    @Select("select * from info_teacher where idcard_number = #{idCard} and password = #{password}")
    List<Teacher> findTeacherByAccountAndPsd(@Param("idCard") String idCard, @Param("password") String password);

    @Select("select * from info_student where idcard_number = #{idCard} and password = #{password}")
    List<Student> findStudentByAccountAndPsd(@Param("idCard") String idCard, @Param("password") String password);

    @Select("select * from info_teacher where id = #{id}")
    List<Teacher> findTeacherById(int id);

    @Select("select * from info_teacher where name like #{name}")
    List<Teacher> findTeacherByName(String name);

    @Select("select * from info_student where id = #{id}")
    List<Student> findStudentById(int id);

    @Insert("insert into info_student(name,idcard_number,password,sex,age,major,clazz,student_number,headimg_url) values(#{name},#{idcard_number},#{password},#{sex},#{age},#{major},#{clazz},#{student_number},#{headimg_url})")
    void addStudent(Student student);

    @Insert("insert into info_teacher(name,idcard_number,password,sex,age ,img_url) values(#{name},#{idcard_number},#{password},#{sex},#{age},#{img_url})")
    void addTeacher(Teacher teacher);

    @Select("select * from info_admin where name = #{name} and password = #{psd}")
    List<Admin> findAdmin(@Param("name") String name, @Param("psd") String psd);

    @Select("select * from info_student ")
    List<Student> findAllStudents();

    @Select("select * from info_teacher")
    List<Teacher> findAllTeachers();

    @Update("update info_student set name = #{name},idcard_number = #{idcard_number},password = #{password},sex = #{sex},age = #{age},major = #{major},clazz = #{clazz},student_number = #{student_number},headimg_url = #{headimg_url} where id = #{id}")
    void updateStudent(Student student);

    @Update("update info_teacher set name = #{name},idcard_number = #{idcard_number},password = #{password},sex = #{sex},age = #{age},img_url = #{img_url} where id = #{id}")
    void updateTeacher(Teacher teacher);

    @Delete("delete from info_student where id = #{id}")
    void deleteStudent(int id);

    @Delete("delete from info_teacher where id = #{id}")
    void deleteTeacher(int id);

    @Update("update info_teacher set password = #{password} where id = #{id}")
    void updateTeacherPsd(@Param("id") int id, @Param("password") String password);

    @Update("update info_student set password = #{password} where id = #{id}")
    void updateStudentPsd(@Param("id") int id, @Param("password") String password);

    @Update("update info_teacher set img_url = #{url} where id = #{id}")
    void updateTeacherImg(@Param("id") int id, @Param("url") String imgUrl);

    @Update("update info_student set headimg_url = #{url} where id = #{id}")
    void updateStudentImg(@Param("id") int id, @Param("url") String imgUrl);

    @Select("select * from info_student where major = #{major} and clazz = #{clazz}")
    List<Student> findStudentsByMajorAndClazz(@Param("major") String major, @Param("clazz") String clazz);

    @Select("select * from info_student where name like #{name}")
    List<Student> findStudentByName(String name);
}
