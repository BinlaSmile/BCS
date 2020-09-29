package com.binla.bcs.model;

public class AuthResultModel {
    private String userCode;
    private String token;

    public AuthResultModel(String userCode, String token) {
        this.userCode = userCode;
        this.token = token;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
