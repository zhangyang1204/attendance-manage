package cn.zyity.attendancemanage.dao;

import cn.zyity.attendancemanage.bean.MyMessage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageDao {

    @Select("select * from info_my_message where id = #{id}")
    List<MyMessage> findById(int id);

    @Select("select mid from map_teacher_message where tid = #{id}")
    List<Integer> findByTid(int id);

    @Select("select * from info_my_message where major = #{major} and clazz = #{clazz}")
    List<MyMessage> findByMajorAndClazz(@Param("major") String major, @Param("clazz") String clazz);

    @Delete("delete from map_teacher_message where tid = #{tid} and mid = #{mid}")
    void deleteTMsg(@Param("tid") int tid, @Param("mid") int mid);

    @Delete("delete from map_stu_message where sid = #{sid} and mid = #{mid}")
    void deleteSMsg(@Param("sid") int sid, @Param("mid") int mid);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into info_my_message(title,content,tid,tname,major,clazz,time) values(#{title},#{content},#{tid},#{tname},#{major},#{clazz},#{time})")
    void add(MyMessage message);

    @Insert("insert into map_teacher_message(tid,mid) values(#{tid},#{mid})")
    void addTMap(@Param("mid") int mid, @Param("tid") int tid);

    @Insert("insert into map_stu_message(sid,mid) values(#{sid},#{mid})")
    void addSMap(@Param("mid") int mid, @Param("sid") int sid);


    @Select("select mid from map_stu_message where sid = #{id}")
    List<Integer> findBySid(int id);

}
