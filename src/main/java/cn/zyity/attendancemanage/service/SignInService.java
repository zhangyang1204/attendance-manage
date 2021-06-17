package cn.zyity.attendancemanage.service;

import cn.zyity.attendancemanage.dao.ISignInDao;
import cn.zyity.attendancemanage.bean.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {
    @Autowired
    ISignInDao dao;

    public void addSignIn(SignIn signIn) {
        dao.addSignIn(signIn);
    }
}
