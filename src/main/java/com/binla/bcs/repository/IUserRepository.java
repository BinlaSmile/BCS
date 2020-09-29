package com.binla.bcs.repository;

import com.binla.bcs.domain.User;

import java.util.List;

public interface IUserRepository {

    User getByName(String userName);
    User getByNamePassword(String userName,String password);
    User getByCode(String code);
    User getByCodePassword(String code,String password);
    User getById(int id);
    boolean add(User entity);
    boolean deleteById(int id);
    boolean edit(User entity);
    List<String> getPermissionById(int id);
}
