package com.binla.bcs.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "返回结果实体类", description = "结果实体类")
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回的状态代码(Success:0)")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    private Response(CodeMsg cm, Object data){
        this.code = cm.code();
        this.message = cm.message();
        this.data = data;
    }

    private Response(CodeMsg cm){
        this.code = cm.code();
        this.message = cm.message();
    }

    private Response(int code,String message){
        this.code = code;
        this.message = message;
    }

    //成功时调用(只需要传入data实体, 成功状态code和信息都封装在构造方法里的)
    public static Response success(Object data){
        return new Response(CodeMsg.SUCCESS,data);
    }

    public static Response success(){
        return new Response(CodeMsg.SUCCESS);
    }

    //失败时调用

    public static Response error(CodeMsg cm){
        return new Response(cm);
    }

    public static Response error(CodeMsg cm, String message){
        cm.setMessage(cm.message()+"--"+message);
        return new Response(cm);
    }

    public static Response error(int code,String message){
        return new Response(code,message);
    }
}
