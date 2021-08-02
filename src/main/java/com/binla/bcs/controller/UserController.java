package com.binla.bcs.controller;

import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.domain.Page;
import com.binla.bcs.domain.Response;
import com.binla.bcs.model.user.request.*;
import com.binla.bcs.model.user.response.UserInfoModel;
import com.binla.bcs.model.user.response.UserPageInfoModel;
import com.binla.bcs.service.IUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@ResponseResult
@Api(tags = "User")
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    private IUserService userService;

    @RequiresPermissions("U0001")
    @GetMapping("/list")
    public List<UserPageInfoModel> getList(String condition,Integer role,String orderColumn,String orderType){
        GetUserReqModel reqModel = new GetUserReqModel(condition,role,orderColumn,orderType);
        return userService.getList(reqModel);
    }

    @RequiresPermissions("U0001")
    @GetMapping("/pageList")
    public Page<UserPageInfoModel> getPageList(String condition, Integer role, String orderColumn, String orderType,
                                               @RequestParam int pageIndex, @RequestParam int pageSize){
        GetPageUserReqModel reqModel = new GetPageUserReqModel(condition,role,orderColumn,orderType,pageIndex,pageSize);
        return userService.getPageList(reqModel);
    }

    @RequiresPermissions("U0001")
    @GetMapping("/{code}")
    public UserInfoModel getInfo(@PathVariable String code) {
        return userService.getInfoByCode(code);
    }

    @RequiresPermissions("U0002")
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public Response create(@RequestBody @Valid CreateUserReqModel model) {
        userService.add(model);
        return Response.success();
    }

    @RequiresPermissions("U0003")
    @PutMapping("/{code}")
    @Transactional(rollbackFor = Exception.class)
    public Response edit(@PathVariable String code, @RequestBody @Valid EditUserReqModel model) {
        userService.edit(code, model);
        return Response.success();
    }

    @RequiresPermissions("U0003")
    @PatchMapping("/{code}/password")
    @Transactional(rollbackFor = Exception.class)
    public Response editPassword(@PathVariable String code, @RequestBody @Valid ChangePasswordReqModel model){
        userService.editPassword(code,model);
        return Response.success();
    }

    @RequiresPermissions("U0004")
    @DeleteMapping("/{code}")
    @Transactional(rollbackFor = Exception.class)
    public Response delete(@PathVariable String code) {
        userService.delete(code);
        return Response.success();
    }
}
