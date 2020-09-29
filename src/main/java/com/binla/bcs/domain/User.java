package com.binla.bcs.domain;

import com.binla.bcs.domain.common.BaseEntity;

import java.util.Date;

public class User extends BaseEntity {

    private String code;
    private String name;
    private String password;
    private int role;
    private String salt;
    private String pic;
    private String colorIdentity;

    public User() {
    }

    public User(String code, String name, String password, int role, String salt, String pic, String colorIdentity) {
        this.code = code;
        this.name = name;
        this.password = password;
        this.role = role;
        this.salt = salt;
        this.pic = pic;
        this.colorIdentity = colorIdentity;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getColorIdentity() {
        return colorIdentity;
    }
    public void setColorIdentity(String colorIdentity) {
        this.colorIdentity = colorIdentity;
    }


}
