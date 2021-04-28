package com.binla.bcs.core.handler;

import com.binla.bcs.core.BizException;
import com.binla.bcs.domain.CodeMsg;
import com.binla.bcs.domain.ErrorResponse;
import com.binla.bcs.domain.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

/**
 * 全局异常处理类
 * @RestControllerAdvice(@ControllerAdvice)，拦截异常并统一处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     * @param e	异常对象
     * @param request	request
     * @return	错误结果
     */
    @ExceptionHandler(BizException.class)
    public ErrorResponse bizExceptionHandler(BizException e, HttpServletRequest request) {
        log.error("业务异常！原因: ", e.getMessage());
        return ErrorResponse.fail(e.getCode(), e.getMessage());
    }

    // 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResponse handlerThrowable(Throwable e, HttpServletRequest request) {
        log.error("未知异常！原因: ", e);
        return ErrorResponse.fail(CodeMsg.SYSTEM_ERROR, e);
    }

    // 参数校验异常
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindExcpetion(BindException e, HttpServletRequest request) {
        log.error("参数校验异常！原因：",e);
        return ErrorResponse.fail(CodeMsg.PARAM_IS_INVALID, e, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("参数校验异常！原因：",e);
        return ErrorResponse.fail(CodeMsg.PARAM_IS_INVALID, e, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
