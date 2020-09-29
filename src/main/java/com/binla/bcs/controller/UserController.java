package com.binla.bcs.controller;

import com.binla.bcs.domain.User;
import com.binla.bcs.model.common.ResponseModel;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;


@RestController
@Api("User")
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/getList")
    public ResponseModel getList()
    {
        return ResponseModel.Success();
    }

    @GetMapping("/getInfo/{id}")
    public ResponseModel getInfo(@PathVariable int id)
    {
        User entiy = new User();
        return ResponseModel.Success(entiy);
    }

    @PostMapping("/create")
    public ResponseModel create(@RequestBody User enity)
    {
        return ResponseModel.Success();
    }

    @PutMapping("/update/{id}")
    public ResponseModel edit(@RequestBody User enity, @PathVariable int id)
    {
        return ResponseModel.Success();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel delete(@PathVariable int id)
    {
        return ResponseModel.Success();
    }
}
