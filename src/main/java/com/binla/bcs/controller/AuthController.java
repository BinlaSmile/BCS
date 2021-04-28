package com.binla.bcs.controller;

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
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/token")
    @ApiOperation(value = "获取授权")
    public Response getAuthToken (@RequestBody LoginModel request) {
        User user = userService.getByCodePassword(request.getUserCode(),request.getPassword());
        if(user!=null){
            String token = JwtUtil.sign(request.getUserCode(), request.getPassword());
            return Response.success(new AuthResultModel(user.getCode(),token));
        }
        else{
            return Response.error(CodeMsg.NAME_OR_PASSWORD_ERROR);
        }
    }

}
