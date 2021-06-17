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
public class MyMessage implements Serializable {
    private int id;
    private String title;
    private String content;
    private int tid;
    private String tname;
    private String major;
    private String clazz;
    private Timestamp time;

    public MyMessage(String title, String content, int tid, String tname, String major, String clazz, Timestamp time) {
        this.title = title;
        this.content = content;
        this.tid = tid;
        this.tname = tname;
        this.major = major;
        this.clazz = clazz;
        this.time = time;
    }
}
