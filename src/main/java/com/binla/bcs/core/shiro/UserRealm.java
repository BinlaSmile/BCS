package com.binla.bcs.core.shiro;

import com.binla.bcs.core.jwt.JwtToken;
import com.binla.bcs.domain.enums.CodeMsg;
import com.binla.bcs.entity.User;
import com.binla.bcs.model.permission.PermissionInfoModel;
import com.binla.bcs.service.IPermissionService;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import com.binla.bcs.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IPermissionService permissionService;

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
            authorizationInfo.addRole(String.valueOf(user.getRole()));
            List<PermissionInfoModel> permissionInfoList = permissionService.getPermissionInfoByRole(user.getRole(),true);
            for(PermissionInfoModel item : permissionInfoList){
                authorizationInfo.addStringPermission(item.getPermissionCode());
            }
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
        if (StringUtil.isNullOrEmpty(userCode))
            throw new AuthenticationException(CodeMsg.TOKEN_INVALID.message());

        User user = userService.getByCode(userCode);
        if (user == null)
            throw new UnknownAccountException();

        if(!JwtUtil.verify(token, userCode,user.getPassword()))
            throw new AuthenticationException(CodeMsg.TOKEN_INVALID.message());

        return new SimpleAuthenticationInfo(user,token, ByteSource.Util.bytes(UUID.randomUUID().toString().replaceAll("-","")),getName());
    }
}
