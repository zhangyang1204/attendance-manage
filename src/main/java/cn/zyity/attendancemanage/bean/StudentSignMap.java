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
public class StudentSignMap implements Serializable {
    private int id;
    private int student_id;
    private int sign_id;
    private Timestamp time;

    public StudentSignMap(int student_id, int sign_id, Timestamp timestamp) {
        this.student_id = student_id;
        this.sign_id = sign_id;
        this.time = timestamp;
    }
}
