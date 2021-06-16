package com.binla.bcs.controller;

import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.entity.User;
import com.binla.bcs.domain.Response;

import com.binla.bcs.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ResponseResult
@Api(tags = "User")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getList")
    public List<User> getList()
    {
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
