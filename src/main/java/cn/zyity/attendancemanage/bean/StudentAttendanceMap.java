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
public class StudentAttendanceMap implements Serializable {
    private int id;
    private int student_id;
    private int attendance_id;
    private String img_url;
    private Timestamp time;
    private String location;
}
