package com.binla.bcs.controller;

import com.binla.bcs.entity.User;
import com.binla.bcs.domain.Response;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "User")
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/getList")
    public Response getList()
    {
        return Response.success();
    }

    @GetMapping("/getInfo/{id}")
    public Response getInfo(@PathVariable int id)
    {
        User entiy = new User();
        return Response.success(entiy);
    }

    @PostMapping("/create")
    public Response create(@RequestBody User enity)
    {

        return Response.success();
    }

    @PutMapping("/update/{id}")
    public Response edit(@RequestBody User enity, @PathVariable int id)
    {
        return Response.success();
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable int id)
    {
        return Response.success();
    }
}
