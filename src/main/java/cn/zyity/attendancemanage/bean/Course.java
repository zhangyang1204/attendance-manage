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
public class Course implements Serializable {
    private int id;
    private String name;
    private String introduce;
    private int teacher_id;
    private int hour;
    private String invite_code;
    private String img_url;


    public Course(String name, String introduce, int teacher_id, int hour, String invite_code, String img_url) {
        this.name = name;
        this.introduce = introduce;
        this.teacher_id = teacher_id;
        this.hour = hour;
        this.invite_code = invite_code;
        this.img_url = img_url;
    }

}
