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
public class StudentDiscussionMap implements Serializable {
    private int id;
    private int student_id;
    private int discussion_id;
    private String content;

}
