package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.Discussion;
import cn.zyity.attendancemanage.bean.Student;
import cn.zyity.attendancemanage.bean.StudentDiscussion;
import cn.zyity.attendancemanage.bean.StudentDiscussionMap;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiscussionDao {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into info_discussion(description,initiator,deadline,course_id) values(#{description},#{initiator},#{deadline},#{course_id})")
    void add(Discussion discussion);

    @Select("SELECT m.content AS content, s.name AS student_name ,s.headimg_url AS img_url FROM info_student s,map_stu_discussion m WHERE s.id = m.student_id AND s.id = #{id} and m.discussion_id = #{did}")
    List<StudentDiscussion> findByStudentId(@Param("id") int id,@Param("did")int did);

    @Select("select * from info_discussion where id = #{id}")
    List<Discussion> findById(int id);

    @Select("select * from info_discussion where course_id = #{cid}")
    List<Discussion> findByCourseId(int cid);

    @Select("SELECT m.content AS content, s.name AS student_name ,s.headimg_url AS img_url FROM info_student s,map_stu_discussion m,map_stu_course msc,info_discussion d WHERE s.id = m.student_id AND s.id = msc.sid and msc.cid = d.course_id and d.id = #{did} and m.discussion_id = #{did} ")
    List<StudentDiscussion> findByDiscussionId(int did);

    @Select("select s.* from info_student s,map_stu_course msc,info_discussion d where s.id = msc.sid and msc.cid = d.course_id and d.id = #{id}")
    List<Student> findStudentsByDiscussionId(int id);

    @Select("SELECT s.* FROM info_student s,map_stu_course msc,info_discussion d,map_stu_discussion msd WHERE s.id = msc.sid AND msc.cid = d.course_id AND d.id = #{id} AND msd.discussion_id = d.id AND msd.student_id = s.id")
    List<Student> findCompletedDiscussionsStudents(int id);

    @Insert("INSERT into map_stu_discussion(student_id,discussion_id,content) values(#{student_id},#{discussion_id},#{content})")
    void addStudentDiscussionMap(StudentDiscussionMap discussion);

    @Delete("delete from map_stu_discussion where id = #{id} ")
    void deleteMap(int id);

    @Select("select id from map_stu_discussion where discussion_id = #{did} and student_id = #{sid}")
    List<Integer> findMap(@Param("did") int did, @Param("sid") int sid);
    @Select("select count(*) from map_stu_discussion m,info_discussion d where m.discussion_id = d.id and d.course_id = #{cid}")
    int findDiscussedCountByCid(@Param("cid") int cid);

    @Select("SELECT m.content AS content, s.name AS student_name,s.id as sid,m.id as id,s.headimg_url AS img_url FROM info_student s,map_stu_discussion m WHERE s.id = m.student_id AND  m.discussion_id = #{id} ")
    List<StudentDiscussion> findAllStudentDiscussionsById(int id);

}
