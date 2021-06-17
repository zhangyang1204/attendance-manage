package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAttendanceDao {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into info_attendance(attendance_item,initiator,way,deadline,major,clazz) values(#{attendance_item},#{initiator},#{way},#{deadline},#{major},#{clazz})")
    void add(Attendance attendance);

    @Select("SELECT s.name AS NAME,s.headimg_url AS headimg,msa.img_url AS photo, msa.location as location FROM info_student s,map_stu_attendance msa,info_attendance a WHERE msa.attendance_id = #{id} AND msa.student_id = s.id AND msa.attendance_id = a.id AND a.major = s.major AND a.clazz = s.clazz;")
    List<AttendanceItem> findSignedItem(int id);

    @Select("select * from info_attendance where id = #{id}")
    List<Attendance> findById(int id);

    @Select("select s.* from map_stu_attendance msa, info_student s where s.id = msa.student_id and msa.attendance_id = #{id}")
    List<Student> findCompletedStudentsById(int id);

    @Select("select s.* from info_student s,info_attendance a where s.major = a.major and s.clazz = a.clazz and a.id = #{id}")
    List<Student> findAllStudentsByAttendanceId(int id);

    @Select("SELECT a.* FROM info_attendance a, info_student s WHERE a.major = s.major AND a.clazz = s.clazz and s.id = #{id}")
    List<Attendance> findAllByStudentId(int id);

    @Select("SELECT m.attendance_id FROM map_stu_attendance m WHERE m.student_id = #{id}")
    List<Integer> findCompletedAttendanceByStudentId(int id);


    /*@Select("SELECT s.* FROM info_student s,info_attendance a,map_stu_attendance msa  WHERE s.major = a.major AND s.clazz = a.clazz AND a.id = #{id} AND msa.attendance_id = a.id AND msa.student_id = s.id")
    List<Student> findSignedStudentsById(int id);*/

    @Select("select * from map_stu_attendance where student_id = #{sid} and attendance_id = #{aid}")
    List<StudentAttendanceMap> findAttendanceBySidAndAid(@Param("sid") int sid, @Param("aid") int aid);

    @Insert("insert into map_stu_attendance(student_id,attendance_id,time,img_url,location) values(#{student_id},#{attendance_id},#{time},#{img_url},#{location})")
    void addAttendanceMap(StudentAttendanceMap map);

    @Select("SELECT a.* FROM info_attendance a,info_teacher t WHERE a.initiator = t.name AND t.id = #{id}")
    List<Attendance> findByTeacherId(int id);
}
