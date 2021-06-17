package cn.zyity.attendancemanage.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBean implements Serializable {
    private boolean isSuccess;// 是否登录成功
    private String role;//角色
    private int id;
}
