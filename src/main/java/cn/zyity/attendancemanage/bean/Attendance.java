package cn.zyity.attendancemanage.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Attendance implements Serializable {
    private int id;
    private String attendance_item;
    private String initiator;
    private String way;
    private Timestamp deadline;
    private String major;
    private String clazz;

    public Attendance(String attendance_item, String initiator, String way, Timestamp deadline, String major, String clazz) {
        this.attendance_item = attendance_item;
        this.initiator = initiator;
        this.way = way;
        this.deadline = deadline;
        this.major = major;
        this.clazz = clazz;
    }
}
