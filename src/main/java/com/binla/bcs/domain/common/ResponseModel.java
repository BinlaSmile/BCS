package com.binla.bcs.domain.common;

//用法示例
//1. result = ResponseModel.Success(data);// 成功，并返回数据和retCode和message
//2. result = ResponseModel.Success();// 成功，不返回数据，只返回retCode和message
//3. result = ResponseModel.Error(CodeMsg.SERVER_EXCEPTION);// 失败返回错误信息
//4. result = ResponseModel.Error(CodeMsg.SERVER_EXCEPTION,e.toString());// 失败返回错误+扩展错误信息

public class ResponseModel<T> {

    //状态代码 0:成功
    private int retCode;
    //返回消息
    private String message;
    //实体泛型(泛型:所有实体的总类，可以装任何实体)
    private T data;

    //私有的构造方法 防止外部调用
    private ResponseModel(T data){
        this.retCode = 0;
        this.message = "Success";
        this.data = data;
    }

    private ResponseModel(CodeMsg cm){
        this.retCode = cm.getRetCode();
        this.message = cm.getMessage();
    }

    //成功时调用(只需要传入data实体, 成功状态code和信息都封装在构造方法里的)
    public static <T> ResponseModel<T> Success(T data){
        return new ResponseModel<>(data);
    }

    public static <T> ResponseModel<T> Success(){
        return (ResponseModel<T>) Success("");
    }

    //失败时调用
    public static <T> ResponseModel<T> Error(CodeMsg cm){
        return new ResponseModel<T>(cm);
    }
    public static <T> ResponseModel<T> Error(CodeMsg cm,String message){
        cm.setMessage(cm.getMessage()+"--"+message);
        return new ResponseModel<T>(cm);
    }

    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }
    public int getRetCode() {
        return retCode;
    }
}
