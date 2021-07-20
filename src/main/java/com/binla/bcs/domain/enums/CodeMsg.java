package com.binla.bcs.domain.enums;

public enum CodeMsg {

    SUCCESS(0001,"成功"),
    // 通用异常
    SYSTEM_ERROR(1000,"系统异常"),
    SERVER_EXCEPTION(1001,"服务端异常"),
    PARAMETER_ISNULL(1002,"输入参数为空"),
    PARAM_IS_INVALID(1003,"参数错误"),
    NOT_FIND_DATA(1009,"查找不到对应数据"),

    // 用户异常
    NAME_OR_PASSWORD_ERROR(2000,"用户名或密码错误"),
    USER_NOT_EXISTS(2001,"用户不存在"),
    SESSION_NOT_EXISTS(2002,"session数据不存在"),
    LOGIN_TIME_OUT(2003,"登陆已过期,请重新登陆"),
    TOKEN_INVALID(2004,"token校验未通过"),
    PERMISSION_DENIED(2005,"用户权限不足");
    private final int code;
    private String message;

    CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }
    public String message() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
