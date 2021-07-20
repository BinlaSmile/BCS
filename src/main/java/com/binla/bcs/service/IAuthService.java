package com.binla.bcs.service;

import com.binla.bcs.entity.User;
import com.binla.bcs.model.auth.CurrentUserModel;

public interface IAuthService {

    String authLogin(String userCode,String password);

    String refreshToken();

    CurrentUserModel getCurrentUser();

    boolean logout();
}
