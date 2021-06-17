package cn.zyity.attendancemanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDetail {
    private String name;
    private int hour;
    private String invite_code;
    private int signCount;
    private double signRate;
    private int discussionCount;
    private double discussionRate;
    private int studentCount;
    int cid;
}
