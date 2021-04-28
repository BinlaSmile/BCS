package com.binla.bcs.controller;

import com.binla.bcs.core.BizException;
import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.entity.User;
import com.binla.bcs.model.AuthResultModel;
import com.binla.bcs.model.LoginModel;
import com.binla.bcs.domain.CodeMsg;
import com.binla.bcs.domain.Response;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "Auth")
@RequestMapping("/api/auth")
@ResponseResult
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/token")
    @ApiOperation(value = "获取授权")
    public AuthResultModel getAuthToken (@RequestBody LoginModel request) {
        User user = userService.getByCodePassword(request.getUserCode(),request.getPassword());
        if(user!=null){
            String token = JwtUtil.sign(request.getUserCode(), request.getPassword());
            return new AuthResultModel(user.getCode(),token);
        }
        else{
            throw new BizException(CodeMsg.NAME_OR_PASSWORD_ERROR);
        }
    }

}
