package com.binla.bcs.service.impl;

import com.binla.bcs.domain.User;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Override
    public boolean addUser(User entity) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public boolean editUser(User entity) {
        return false;
    }
    @Override
    public User getByNamePassword(String name, String password) {
        return null;
    }
    @Override
    public List<String> getPermission(int id) {
        return userRepository.getPermissionById(id);
    }
}
