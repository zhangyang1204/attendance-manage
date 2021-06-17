package cn.zyity.attendancemanage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {
    private String name;
    private int id;
    private String idcard_number;
    private String password;
    private String sex;
    private int age;
    private String major;
    private String clazz;
    private String student_number;
    private String headimg_url;

}
