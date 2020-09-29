package com.binla.bcs.service.impl;

import com.binla.bcs.domain.User;
import com.binla.bcs.repository.IUserRepository;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.EncryptUtil;
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
    public User getByName(String name) {
        return userRepository.getByName(name);
    }
    @Override
    public User getByNamePassword(String name, String password) {
        User u =userRepository.getByName(name);
        EncryptUtil eu = EncryptUtil.getInstance();
        String enPassword = eu.MD5(password,u.getSalt());
        if(enPassword.equals(u.getPassword())){
            return u;
        }else{
            return null;
        }
    }

    @Override
    public User getByCode(String code) {
        return userRepository.getByCode(code);
    }

    @Override
    public User getByCodePassword(String code, String password) {
        User u =userRepository.getByCode(code);
        if(u!=null){
            EncryptUtil eu = EncryptUtil.getInstance();
            String enPassword = eu.MD5(password,u.getSalt());
            if(enPassword.equals(u.getPassword())){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<String> getPermission(int id) {
        return userRepository.getPermissionById(id);
    }
}
