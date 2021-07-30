package com.binla.bcs.core.shiro;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.binla.bcs.core.jwt.JwtToken;
import com.binla.bcs.domain.constants.SecurityConstant;
import com.binla.bcs.domain.enums.CodeMsg;
import com.binla.bcs.entity.User;
import com.binla.bcs.model.auth.CurrentUserModel;
import com.binla.bcs.model.permission.PermissionInfoModel;
import com.binla.bcs.model.user.response.UserDataModel;
import com.binla.bcs.service.IPermissionService;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import com.binla.bcs.utils.RedisUtil;
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
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
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

        UserDataModel user = userService.getDataByCode(userCode);
        if (user == null)
            throw new UnknownAccountException();

        Long cacheExpireTime = ((Integer)redisUtil.get(SecurityConstant.AUTH_REFRESH_TOKEN + userCode)).longValue();
        Long expireTime = JwtUtil.getExpireTime(token);
        if(!cacheExpireTime.equals(expireTime))
            throw new AuthenticationException(CodeMsg.TOKEN_INVALID.message());

        try{
            JwtUtil.verify(token, userCode,user.getPassword());
        }catch (TokenExpiredException e){
            throw new TokenExpiredException("["+userCode+"]token已过期,token:"+token);
        }catch (Exception e){
            throw new AuthenticationException(CodeMsg.TOKEN_INVALID.message());
        }
        CurrentUserModel currentUser = new CurrentUserModel(user.getCode(),user.getName(),user.getRole(),"",user.getPic(),user.getColor());
        return new SimpleAuthenticationInfo(currentUser,token, ByteSource.Util.bytes(UUID.randomUUID().toString().replaceAll("-","")),getName());
    }

    /**
     * 验证权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        CurrentUserModel user  = (CurrentUserModel) principals.getPrimaryPrincipal();
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


}
