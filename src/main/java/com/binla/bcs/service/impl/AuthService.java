package com.binla.bcs.service.impl;

import com.binla.bcs.core.jwt.JwtToken;
import com.binla.bcs.entity.User;
import com.binla.bcs.model.auth.CurrentUserModel;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.utils.EncryptUtil;
import com.binla.bcs.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public String authLogin(String userCode,String password) {
        try {
            User user = userRepository.getByCode(userCode);
            EncryptUtil eu = EncryptUtil.getInstance();
            String enPassword = eu.MD5(password,user.getSalt());
            String token = JwtUtil.sign(userCode,enPassword);
            Subject subject = SecurityUtils.getSubject();
            subject.login(new JwtToken(token));
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String refreshToken() {
        User currentUser = (User)SecurityUtils.getSubject().getPrincipal();
        User entity = userRepository.getByCode(currentUser.getCode());
        if(currentUser.getPassword().equals(entity.getPassword())){}

        return null;
    }

    @Override
    public CurrentUserModel getCurrentUser() {
        return (CurrentUserModel)SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public boolean logout() {
        return false;
    }
}
