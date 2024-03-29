package com.binla.bcs.core.handler;

import com.binla.bcs.core.BizException;
import com.binla.bcs.domain.enums.CodeMsg;
import com.binla.bcs.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException e) {
        log.error("参数校验异常！原因：",e);
        return ErrorResponse.fail(CodeMsg.PARAM_IS_INVALID, e, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("参数校验异常！原因：",e);
        return ErrorResponse.fail(CodeMsg.PARAM_IS_INVALID, e, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        log.error("用户权限不足：",e);
        return ErrorResponse.fail(CodeMsg.PERMISSION_DENIED, e, e.getMessage());
    }
}
