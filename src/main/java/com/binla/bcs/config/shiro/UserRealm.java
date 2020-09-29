package com.binla.bcs.config.shiro;

import com.alibaba.fastjson.JSONObject;
import com.binla.bcs.config.jwt.JwtToken;
import com.binla.bcs.domain.User;
import com.binla.bcs.service.ILoginService;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import com.google.common.base.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);
//    @Autowired
//    private ILoginService loginService;
    @Autowired
    private IUserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User) principals.getPrimaryPrincipal();
        if (user == null) {
            logger.error("授权失败，用户信息为空！！！");
            return null;
        }
        try {
            //获取用户角色集
//            Set<String> listRole= roleService.findRoleByUsername(user.getUserName());
//            simpleAuthorizationInfo.addRoles(listRole);
//
//            //通过角色获取权限集
//            for (String role : listRole) {
//                Set<String> permission= permissionService.findPermissionByRole(role);
//                simpleAuthorizationInfo.addStringPermissions(permission);
//            }
            return authorizationInfo;
        } catch (Exception e) {
            logger.error("授权失败，请检查系统内部错误!!!", e);
        }
        return authorizationInfo;
//        Session session = SecurityUtils.getSubject().getSession();
//        //查询用户的权限
//        JSONObject permission = (JSONObject) session.getAttribute("userPermission");
//        logger.info("permission的值为:" + permission);
//        logger.info("本用户权限为:" + permission.get("permissionList"));
//        //为当前用户设置角色和权限
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
//        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        String token = (String) authcToken.getCredentials();// 校验token有效性
        String userCode = JwtUtil.getUserCode(token);
        if (Strings.isNullOrEmpty(userCode)) {
            throw new AuthenticationException("token非法无效!");
        }// 查询用户信息
        User user = userService.getByCode(userCode);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user,token, ByteSource.Util.bytes(UUID.randomUUID().toString().replaceAll("-","")),getName());
//
//        String loginName = (String) authcToken.getPrincipal();
//        // 获取用户密码
//        String password = new String((char[]) authcToken.getCredentials());
//        User user = loginService.getUser(loginName, password);
//        if (user == null) {
//            //没找到帐号
//            throw new UnknownAccountException();
//        }
//        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                user.getUsername(),
//                user.getPassword(),
//                getName()
//        );
//        //session中不需要保存密码
//        user.setPassword("");
//        //将用户信息放入session中
//        SecurityUtils.getSubject().getSession().setAttribute("userInfo", user);
//        return authenticationInfo;
    }
}
