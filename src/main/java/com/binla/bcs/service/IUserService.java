package com.binla.bcs.service;

import com.binla.bcs.domain.User;

public interface IUserService {
    boolean addUser(User entity);
    boolean deleteUser(int id);
    boolean editUser(User entity);
    boolean login(User entity);
}
