package com.binla.bcs.service;

import com.binla.bcs.domain.User;

import java.util.List;

public interface IUserService {
    boolean addUser(User entity);
    boolean deleteUser(int id);
    boolean editUser(User entity);
    User getByNamePassword(String username, String password);
    List<String> getPermission(int id);
}
