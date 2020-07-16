package com.binla.bcs.controller;

import com.binla.bcs.domain.User;
import com.binla.bcs.domain.common.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseModel login(@RequestBody User user){
        String userName = user.getUsername();
        String password = user.getPassword();
        return ResponseModel.Success();
    }

}
