package com.binla.bcs.service;

import com.binla.bcs.entity.User;

public interface ILoginService {

    boolean authLogin(String username,String password);

    User getUser(String username, String password);

    User getInfo();

    boolean logout();

}
