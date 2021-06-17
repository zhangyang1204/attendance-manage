package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.CourseMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequestMapping("/courseMessage")
public interface ICourseMessageDao {

    @Insert("insert into info_course_message(course_id,owner_role,type,owner_id,content,message_id) values(#{course_id},#{owner_role},#{type},#{owner_id},#{content},#{message_id})")
    void add(CourseMessage message);

    @Select("select * from info_course_message")
    List<CourseMessage> findAll();


}
