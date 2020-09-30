package com.binla.bcs.controller;

import com.alibaba.fastjson.JSONObject;
import com.binla.bcs.domain.User;
import com.binla.bcs.model.AuthResultModel;
import com.binla.bcs.model.LoginModel;
import com.binla.bcs.model.common.CodeMsg;
import com.binla.bcs.model.common.ResponseModel;
import com.binla.bcs.service.ILoginService;
import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "Auth")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/token")
    @ApiOperation(value = "获取授权")
    public ResponseModel<AuthResultModel> getAuthToken (@RequestBody LoginModel request) {
        User user = userService.getByCodePassword(request.getUserCode(),request.getPassword());
        if(user!=null){
            String token = JwtUtil.sign(request.getUserCode(), request.getPassword());
            return ResponseModel.Success(new AuthResultModel(user.getCode(),token));
        }
        else{
            return ResponseModel.Error(CodeMsg.NAME_OR_PASSWORD_ERROR);
        }
    }

}
