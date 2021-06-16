package com.binla.bcs.core.shiro;

import com.binla.bcs.core.jwt.JwtToken;
import com.binla.bcs.entity.User;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            log.error("授权失败，用户信息为空!");
            return null;
        }
        try {
            return authorizationInfo;
        } catch (Exception e) {
            log.error("授权失败,原因:", e);
        }
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        String token = (String) authToken.getCredentials();
        String userCode = JwtUtil.getUserCode(token);
        if (Strings.isNullOrEmpty(userCode))
            throw new AuthenticationException("token校验未通过");

        if(!JwtUtil.verify(token, userCode))
            throw new AuthenticationException("token校验未通过");

        User user = userService.getByCode(userCode);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user,token, ByteSource.Util.bytes(UUID.randomUUID().toString().replaceAll("-","")),getName());
    }
}
