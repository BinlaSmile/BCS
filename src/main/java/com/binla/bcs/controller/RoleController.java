package com.binla.bcs.controller;

import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.model.role.response.RoleModel;
import com.binla.bcs.model.user.request.GetUserReqModel;
import com.binla.bcs.model.user.response.UserPageInfoModel;
import com.binla.bcs.service.IRoleService;
import com.binla.bcs.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseResult
@Api(tags = "Role")
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("R0001")
    @GetMapping("/list")
    public List<RoleModel> getList(){
        return roleService.getAll();
    }
}
