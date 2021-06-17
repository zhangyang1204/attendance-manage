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
public class StudentDiscussion implements Serializable {
    private int id;
    private int sid;
    private String img_url;
    private String student_name;
    private String content;
}
