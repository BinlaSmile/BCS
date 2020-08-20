package com.binla.bcs.controller;

import com.alibaba.fastjson.JSONObject;
import com.binla.bcs.domain.User;
import com.binla.bcs.domain.common.CodeMsg;
import com.binla.bcs.domain.common.ResponseModel;
import com.binla.bcs.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("Auth")
@RequestMapping("/api/Auth")
public class AuthController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public ResponseModel authLogin(@RequestBody JSONObject requestJson) {
        String userName = requestJson.getString("username");
        String password = requestJson.getString("password");
        if(loginService.authLogin(userName,password)){
            return ResponseModel.Success();
        }
        else{
            return ResponseModel.Error(CodeMsg.NAME_OR_PASSWORD_ERROR);
        }
    }

    @GetMapping("/getInfo")
    public ResponseModel getInfo() {
        return ResponseModel.Success(loginService.getInfo());
    }

    @PostMapping("/logout")
    public ResponseModel logout() {
        if(loginService.logout()){
            return ResponseModel.Success();
        }else{
            return ResponseModel.Error(CodeMsg.SERVER_EXCEPTION);
        }
    }

}
