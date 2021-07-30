package com.binla.bcs.service;

import com.binla.bcs.entity.User;
import com.binla.bcs.model.auth.CurrentUserModel;

public interface IAuthService {
    /**
     * 授权登录，一般用于用户登录操作，需要用户名密码
     * @param userCode 用户代码
     * @param password 密码
     * @return 授权令牌
     */
    String authLogin(String userCode,String password);

    /**
     * 用户已登录用户获取新令牌，只需要用户名，密码将会从数据库中获取
     * @param userCode 用户代码
     * @return 授权令牌
     */
    String authLogin(String userCode);

    CurrentUserModel getCurrentUser();

    String getCurrentUserCode();

    boolean logout();
}
