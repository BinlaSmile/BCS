package com.binla.bcs.controller;

import com.binla.bcs.core.BizException;
import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.entity.User;
import com.binla.bcs.model.auth.AuthResultModel;
import com.binla.bcs.model.auth.LoginModel;
import com.binla.bcs.domain.enums.CodeMsg;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import com.binla.bcs.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseResult
@Api(tags = "Auth")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/getToken")
    @ApiOperation(value = "获取授权")
    public AuthResultModel getAuthToken(@RequestBody LoginModel request) {
        String token = authService.authLogin(request.getUserCode(),request.getPassword());
        if(!StringUtil.isNullOrEmpty(token))
            return new AuthResultModel(request.getUserCode(),token);
        else
            throw new BizException(CodeMsg.NAME_OR_PASSWORD_ERROR);
    }

    @PostMapping("/refreshToken")
    @ApiOperation(value = "刷新授权")
    public AuthResultModel getRefreshToken() {
        User user = new User();
        if(user!=null){
            String token = "";//JwtUtil.sign(request.getUserCode());
            return new AuthResultModel(user.getCode(),token);
        }
        else{
            throw new BizException(CodeMsg.NAME_OR_PASSWORD_ERROR);
        }
    }
}
