package com.binla.bcs.service;

import com.binla.bcs.entity.User;

import java.util.List;

public interface IUserService {
    boolean addUser(User entity);
    boolean deleteUser(int id);
    boolean editUser(User entity);
    User getByName(String username);
    User getByNamePassword(String username, String password);
    User getByCode(String code);
    User getByCodePassword(String code,String password);
    List<User> getAll();
}
