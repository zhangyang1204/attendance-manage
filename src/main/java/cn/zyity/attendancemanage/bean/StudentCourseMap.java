package cn.zyity.attendancemanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCourseMap implements Serializable {
    private int id;
    private int sid;
    private int cid;
}
