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
public class Teacher implements Serializable {
    private int id;
    private String name;
    private String idcard_number;
    private String password;
    private String sex;
    private int age;
    private String img_url;
}
