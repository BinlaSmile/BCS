package com.binla.bcs.domain.common;

public class CodeMsg {
    private int retCode;
    private String message;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0001,"success");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(1001,"服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(1002,"输入参数为空");
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(1009,"查找不到对应数据");
    // 用户异常
    public static CodeMsg NAME_OR_PASSWORD_ERROR = new CodeMsg(2000,"用户名或密码错误");
    public static CodeMsg USER_NOT_EXSIST = new CodeMsg(2001,"用户不存在");
    public static CodeMsg SESSION_NOT_EXSIST =  new CodeMsg(2002,"session数据不存在");
    public static CodeMsg LOGIN_TIME_OUT = new CodeMsg(2003,"登陆已过期,请重新登陆");


    private CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }
    public int getRetCode() {
        return retCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return retCode+ ":" + message;
    }
}
