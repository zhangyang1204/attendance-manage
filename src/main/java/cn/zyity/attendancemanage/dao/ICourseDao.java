package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseDao {

    @Insert("insert into info_course(name,introduce,teacher_id,hour,invite_code,img_url) values(#{name},#{introduce},#{teacher_id},#{hour},#{invite_code},#{img_url})")
    void addCourse(Course course) ;

    @Select("select * from info_course")
    List<Course> findAllCourse();
    @Select("select * from info_course where id = #{id}")
    List<Course> findCourseById(int id);

    @Select("select * from info_course where name like #{name} and teacher_id = #{tid}")
    List<Course> findCourseByName(@Param("name") String name,@Param("tid") int tid);

    @Select("select * from info_course where teacher_id = #{tid} ")
    List<Course> findAllCourseByTeacherId(@Param("tid")int tid);

    @Select("select i.* from info_course i,map_stu_course m where m.sid = #{id} and m.cid = i.id")
    List<Course> findAllCourseByStudentId(int id);

    @Delete("delete from info_course where id = #{id}")
    void delete(int id);

    @Insert("insert into info_course(name,introduce,teacher_id,hour,invite_code,img_url) values(#{name},#{introduce},#{teacher_id},#{hour},#{invite_code},#{img_url})")
    void add(Course course);

    @Select("select teacher_id from info_course where id = #{id}")
    List<Integer> findTeacherIdsByCourseId(int id);

    @Select("SELECT s.* FROM info_student s,map_stu_course m WHERE s.id = m.sid and m.cid = #{cid}")
    List<Student> findStudentsByCid(int cid);

    @Select("SELECT t.* FROM info_teacher t,info_course c WHERE t.id = c.teacher_id and c.id = #{cid}")

    List<Teacher> findTeacherByCid(int cid);

    @Delete("delete from map_stu_course  where sid = #{sid} and cid = {#cid} ")
    void deleteStudentOfCourse(@Param("cid") int cid, @Param("sid") int sid);

    @Insert("insert into map_stu_course(sid,cid) values(#{sid},#{cid})")
    void addStudentCourseMap(StudentCourseMap map);

    @Select("select * from info_course where invite_code = #{invitCode}")
    List<Course> findCourseByInvitecode(String invitCode);

    @Select("select * from info_course_message where course_id = #{cid}")
    List<CourseMessage> findMessagesByCourseId(int cid);

}

