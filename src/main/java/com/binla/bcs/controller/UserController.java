package com.binla.bcs.controller;

import com.binla.bcs.domain.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{id}")
    public User GetUser(@PathVariable("id") Long id){
        return new User(id,"Test",25);
    }
}
