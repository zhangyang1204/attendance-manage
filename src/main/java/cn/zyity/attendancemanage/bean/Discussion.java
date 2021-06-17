package cn.zyity.attendancemanage.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Discussion implements Serializable {
    private int id;
    private int course_id;
    private String description;
    private String initiator;
    private Timestamp deadline;

    public Discussion(int course_id, String description, String initiator, Timestamp deadline) {
        this.course_id = course_id;
        this.description = description;
        this.initiator = initiator;
        this.deadline = deadline;
    }
}
