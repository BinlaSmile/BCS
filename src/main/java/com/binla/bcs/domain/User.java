package com.binla.bcs.domain;

public class User {
    private int uid;
    private  String username;
    private String password;
    public User() {
    }
    public User(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }
    public int getId() {
        return uid;
    }
    public void setId(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{ id=" + uid + ", username='" + username + "'}";
    }
}
