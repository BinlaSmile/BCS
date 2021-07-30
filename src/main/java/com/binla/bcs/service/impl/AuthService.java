package com.binla.bcs.service.impl;

import com.binla.bcs.core.jwt.JwtToken;
import com.binla.bcs.core.properties.JwtProperties;
import com.binla.bcs.domain.constants.SecurityConstant;
import com.binla.bcs.entity.Log;
import com.binla.bcs.entity.User;
import com.binla.bcs.model.auth.CurrentUserModel;
import com.binla.bcs.repository.ILogRepository;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.utils.EncryptUtil;
import com.binla.bcs.utils.JwtUtil;
import com.binla.bcs.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ILogRepository logRepository;

    @Override
    public String authLogin(String userCode,String password) {
        String refreshTokenKey= SecurityConstant.AUTH_REFRESH_TOKEN + userCode;
        try {
            User user = userRepository.getByCode(userCode);
            EncryptUtil eu = EncryptUtil.getInstance();
            String enPassword = eu.MD5(password,user.getSalt());
            String token = JwtUtil.sign(userCode,enPassword);
            Long expireTime = JwtUtil.getExpireTime(token);
            //需要先把refreshToken放入缓存用于校验
            redisUtil.set(refreshTokenKey, expireTime, JwtProperties.getRefreshExpireTime()*60);
            Subject subject = SecurityUtils.getSubject();
            subject.login(new JwtToken(token));
            logRepository.insert(new Log(1,"登录系统",getCurrentUserCode(),new Date()));
            return token;
        } catch (Exception e) {
            redisUtil.del(refreshTokenKey);
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public String authLogin(String userCode) {
        String token = null;
        String refreshTokenKey= SecurityConstant.AUTH_REFRESH_TOKEN + userCode;
        String lockName = SecurityConstant.REFRESH_CHECK + userCode;
        boolean locked = redisUtil.getLock(lockName, 60);
        if(locked){
            try{
                User user = userRepository.getByCode(userCode);
                token = JwtUtil.sign(userCode,user.getPassword());
                //更新RefreshToken缓存的时间戳
                Long expireTime = JwtUtil.getExpireTime(token);
                redisUtil.set(refreshTokenKey, expireTime, JwtProperties.getRefreshExpireTime()*60);
                Subject subject = SecurityUtils.getSubject();
                subject.login(new JwtToken(token));
            }catch (Exception e){
                redisUtil.del(refreshTokenKey);
                return null;
            }
        }
        redisUtil.releaseLock(lockName);
        return token;
    }

    @Override
    public CurrentUserModel getCurrentUser() {
        return (CurrentUserModel)SecurityUtils.getSubject().getPrincipal();
    }

    @Override
    public String getCurrentUserCode() {
        return ((CurrentUserModel)SecurityUtils.getSubject().getPrincipal()).getCode();
    }

    @Override
    public boolean logout() {
        return false;
    }
}
