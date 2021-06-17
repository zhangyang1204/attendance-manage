package cn.zyity.attendancemanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignIn implements Serializable {
    private int id;
    private String initiator;
    private String way;
    private Timestamp deadline;
    private int course_id;

    public SignIn(String initiator, String way, Timestamp deadline, int cid) {
        this.initiator = initiator;
        this.way = way;
        this.deadline = deadline;
        this.course_id = cid;
    }
}
