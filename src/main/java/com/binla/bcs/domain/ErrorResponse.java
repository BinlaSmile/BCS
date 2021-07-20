package com.binla.bcs.domain;

import com.binla.bcs.domain.enums.CodeMsg;
import lombok.Data;

@Data
public class ErrorResponse {

    private Integer code;

    private String message;

    private String exception;


    public static ErrorResponse fail(CodeMsg codeMsg, Throwable e, String message) {
        ErrorResponse errorResponse = ErrorResponse.fail(codeMsg, e);
        errorResponse.setMessage(message);
        return errorResponse;
    }

    public static ErrorResponse fail(CodeMsg codeMsg, Throwable e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(codeMsg.code());
        errorResponse.setMessage(codeMsg.message());
        errorResponse.setException(e.getClass().getName());
        return errorResponse;
    }
    public static ErrorResponse fail(Integer code, String message) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setMessage(message);
        return errorResponse;
    }
}
