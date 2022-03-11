package com.binla.bcs.controller;

import com.binla.bcs.core.annotation.ResponseResult;
import com.binla.bcs.service.IColorService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseResult
@Api(tags = "Color")
@RequestMapping("/api/color")
public class ColorController {
    @Autowired
    private IColorService colorService;

    @RequiresPermissions("C9001")
    @GetMapping("/list")
    public List<String> getList(){
        return colorService.getAllCode();
    }
}
