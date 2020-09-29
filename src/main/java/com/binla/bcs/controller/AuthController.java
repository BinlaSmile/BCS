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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("Auth")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseModel<AuthResultModel> authLogin(@RequestBody LoginModel request) {
        User user = userService.getByCodePassword(request.getUserCode(),request.getPassword());
        if(user!=null){
            String token = JwtUtil.sign(request.getUserCode(), request.getPassword());
            return ResponseModel.Success(new AuthResultModel(user.getCode(),token));
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
