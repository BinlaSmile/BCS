package com.binla.bcs.service.impl;

import com.binla.bcs.domain.User;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean authLogin(String username,String password) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @Override
    public User login(String username, String password) {
        return userRepository.getByNamePassWord(username, password);
    }

    @Override
    public User getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        User userInfo = (User) session.getAttribute("userInfo");
        List<String> userPermission = userRepository.getPermissionById(userInfo.getUid());
        session.setAttribute("userPermission", userPermission);
        return userInfo;
    }

    @Override
    public boolean logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
