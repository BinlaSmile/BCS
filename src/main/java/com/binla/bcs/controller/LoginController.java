package com.binla.bcs.controller;

import com.alibaba.fastjson.JSONObject;
import com.binla.bcs.domain.User;
import com.binla.bcs.domain.common.CodeMsg;
import com.binla.bcs.domain.common.ResponseModel;
import com.binla.bcs.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/auth")
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

    @PostMapping("/getInfo")
    public ResponseModel<User> getInfo() {
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
