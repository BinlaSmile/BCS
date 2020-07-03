package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

import java.util.Date;

public class User extends BaseEntity {
    private int uid;
    private  String username;
    private String password;
    private int roles;
    public User() {
    }

    public User(int uid, String username, String password,int roles) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public int getUid() {
        return uid;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public int getRoles() {
        return roles;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setRoles(int roles) {
        this.roles = roles;
    }

    @Override
    public Date getInsertDate() {
        return super.getInsertDate();
    }
    @Override
    public Date getUpdateDate() {
        return super.getUpdateDate();
    }
    @Override
    public String getInsertUser() {
        return super.getInsertUser();
    }
    @Override
    public String getUpdateUser() {
        return super.getUpdateUser();
    }
    @Override
    public void setInsertDate(Date insertDate) {
        super.setInsertDate(insertDate);
    }
    @Override
    public void setInsertUser(String insertUser) {
        super.setInsertUser(insertUser);
    }
    @Override
    public void setUpdateDate(Date updateDate) {
        super.setUpdateDate(updateDate);
    }
    @Override
    public void setUpdateUser(String updateUser) {
        super.setUpdateUser(updateUser);
    }
    @Override
    public String toString() {
        return "User{ id=" + uid + ", username='" + username + "'}";
    }
}
