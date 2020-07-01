package com.binla.bcs.controller;

import com.binla.bcs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @RequestMapping("/{id}")
    public List<User> GetUser(@PathVariable("id") int id){
        String sql = "SELECT * FROM tab_user WHERE uid = ?";
        List<User> a = new ArrayList<>();
        User ua = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
        a.add(ua);
        return a;
    }

}
