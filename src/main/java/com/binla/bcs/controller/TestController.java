package com.binla.bcs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/helloworld")
    public String HelloWorld(){
        return "Hello World!";
    }
}
