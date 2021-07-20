package com.binla.bcs.controller;

import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.entity.User;
import com.binla.bcs.domain.Response;

import com.binla.bcs.service.IUserService;
import com.binla.bcs.utils.RedisUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseResult
@Api(tags = "User")
@RequestMapping("/api/user")
@RequiresPermissions("U0001")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/getList")
    public List<User> getList()
    {
        redisUtil.set("TEST","Test");
        String test = (String)redisUtil.get("TEST");
        return userService.getAll();
    }

    @GetMapping("/getInfo/{id}")
    public Response getInfo(@PathVariable int id)
    {
        User entity = new User();
        return Response.success(entity);
    }

    @PostMapping("/create")
    public Response create(@RequestBody User entity)
    {
        return Response.success();
    }

    @PutMapping("/update/{id}")
    public Response edit(@RequestBody User entity, @PathVariable int id)
    {
        return Response.success();
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable int id)
    {
        return Response.success();
    }
}
