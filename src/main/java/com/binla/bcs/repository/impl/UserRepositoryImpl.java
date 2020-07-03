package com.binla.bcs.repository.impl;

import com.binla.bcs.domain.User;
import com.binla.bcs.repository.IUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Override
    public User getByNamePassWord(String userName, String password) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public boolean add(User entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean edit(User entity) {
        return false;
    }
}
