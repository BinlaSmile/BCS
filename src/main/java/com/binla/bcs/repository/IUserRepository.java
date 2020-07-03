package com.binla.bcs.repository;

import com.binla.bcs.domain.User;

public interface IUserRepository {

    User getByNamePassWord(String userName,String password);
    User getById(int id);
    boolean add(User entity);
    boolean deleteById(int id);
    boolean edit(User entity);
}
