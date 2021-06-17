package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.Course;
import cn.zyity.attendancemanage.bean.SignIn;
import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.bean.StudentSignMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISignInDao {

    @Insert("insert into info_sign_in(initiator,way,deadline,course_id) values(#{initiator},#{way},#{deadline},#{course_id})")
     void addSignIn(SignIn signIn);

    @Select("SELECT si.* FROM  info_sign_in si, map_stu_course m WHERE m.sid = #{id} AND m.cid = si.course_id")
    List<SignIn> findAllSignByStudentId(int id);
    @Select("SELECT si.* FROM  info_sign_in si where si.course_id = #{id}")
    List<SignIn> findAllSignByCourseId(int id);

    @Select("SELECT m.sign_id FROM map_stu_sign m WHERE m.student_id = #{id}")
    List<Integer> findCompletedSignByStudentId(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into info_sign_in(initiator,way,deadline,course_id) values(#{initiator},#{way},#{deadline},#{course_id})")
    void add(SignIn sign);

    @Select("SELECT s.* FROM info_student s,map_stu_course msc,info_sign_in si WHERE s.id = msc.sid AND msc.cid = si.course_id AND si.id = #{id}")
    List<Student> findAllStudentsById(int id);

    @Select("SELECT s.* FROM info_student s,map_stu_course msc,info_sign_in si,map_stu_sign mss  WHERE s.id = msc.sid AND msc.cid = si.course_id AND si.id = mss.sign_id and s.id = mss.student_id and si.id = #{id}")
    List<Student> findSignedStudentsById(int id);

    @Select("select * from info_sign_in where id = #{id}")
    List<SignIn> findById(int id);

    @Select("select c.* from info_course c,info_sign_in s where c.id = s.course_id and s.id = #{id}")
    List<Course> findCourseBySignId(int id);

    @Select("select * from map_stu_sign where sign_id = #{signId} and student_id = #{studentId}")
    List<StudentSignMap> findSignMap(@Param("signId") int signId,@Param("studentId") int studentId);

    @Insert("insert into map_stu_sign(student_id,sign_id,time) values(#{student_id},#{sign_id},#{time})")
    void addSignMap(StudentSignMap map);

    @Select("select count(*) from info_sign_in where course_id = #{cid}")
    int findCountByCid(int cid);

    @Select("select count(*) from map_stu_sign m,info_sign_in s where m.sign_id = s.id and s.course_id = #{cid}")
    int findSignedCountByCid(@Param("cid") int cid);

    @Select("SELECT s.* FROM info_sign_in s,info_course c WHERE s.course_id = c.id AND c.teacher_id = #{id}")
    List<SignIn> findByTeacherId(int id);

}
