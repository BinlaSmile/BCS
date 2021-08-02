package com.binla.bcs.controller;

import com.binla.bcs.core.BizException;
import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.domain.Response;
import com.binla.bcs.domain.constants.SecurityConstant;
import com.binla.bcs.model.auth.AuthResultModel;
import com.binla.bcs.model.auth.LoginModel;
import com.binla.bcs.domain.enums.CodeMsg;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@Api(tags = "Auth")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping
    @ApiOperation(value = "获取授权")
    @ResponseResult
    public AuthResultModel getAuthToken(@RequestBody LoginModel request, HttpServletResponse response) {
        String token = authService.authLogin(request.getUserCode(),request.getPassword());
        response.setHeader(SecurityConstant.AUTH_HEADER, token);
        response.setHeader("Access-Control-Expose-Headers", SecurityConstant.AUTH_HEADER);
        return new AuthResultModel(request.getUserCode(),token);
    }

    @DeleteMapping("/logout")
    @ApiOperation(value = "当前用户退出登录")
    @ResponseResult
    public Response authLogout(){
        authService.logout();
        return Response.success();
    }
}
