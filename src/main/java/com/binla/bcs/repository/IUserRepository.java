package com.binla.bcs.repository;

import com.binla.bcs.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository {
    User getByName(String userName);
    User getByNamePassword(String userName,String password);
    User getByCode(String code);
    User getByCodePassword(String code,String password);
    User getById(int id);
    List<User> getAll();
    boolean add(User entity);
    boolean deleteById(int id);
    boolean edit(User entity);
}
