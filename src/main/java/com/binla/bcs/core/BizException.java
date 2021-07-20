package com.binla.bcs.core;

import com.binla.bcs.domain.enums.CodeMsg;
import lombok.Data;

@Data
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public BizException() {
        super();
    }

    public BizException(CodeMsg codeMsg) {
        super(codeMsg.message());
        this.code = codeMsg.code();
        this.message = codeMsg.message();
    }

    public BizException(CodeMsg codeMsg, Throwable cause) {
        super(codeMsg.message(), cause);
        this.code = codeMsg.code();
        this.message = codeMsg.message();
    }

    public BizException(String message) {
        super(message);
        this.code = -1;
        this.message = message;
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}