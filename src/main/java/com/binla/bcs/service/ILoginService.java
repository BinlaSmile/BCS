package com.binla.bcs.service;

import com.alibaba.fastjson.JSONObject;
import com.binla.bcs.domain.User;

public interface ILoginService {

    boolean authLogin(String username,String password);

    User login(String username, String password);

    User getInfo();

    boolean logout();

}
