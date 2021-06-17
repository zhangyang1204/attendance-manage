package cn.zyity.attendancemanage.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseMessage implements Serializable {
//    数据库表
    private int id;
    private int course_id;//课程id
 /*   message_id指数据库中该条记录对应其他表中的id比如签到id或讨论id,
    用于查询签到信息或讨论信息，若消息类型为文本则用不到该属性
 */   private int message_id;
    private String owner_role;//消息发送方是老师或学生
    private int type;//消息类型，文字，签到，或讨论
    private int owner_id;//发送方id
    private String content;//消息内容，当消息类型为文字时为其值

    public CourseMessage(int course_id, int message_id, String owner_role, int type, int owner_id, String content) {
        this.course_id = course_id;
        this.message_id = message_id;
        this.owner_role = owner_role;
        this.type = type;
        this.owner_id = owner_id;
        this.content = content;
    }
}
