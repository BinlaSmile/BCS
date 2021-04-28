package com.binla.bcs.controller;

import com.binla.bcs.entity.Item;
import com.binla.bcs.domain.Response;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Item")
@RequestMapping("/api/item")
public class ItemController {

    @GetMapping("/getList")
    public Response getList()
    {
        return Response.success();
    }

    @GetMapping("/getInfo/{id}")
    public Response getInfo(@PathVariable int id)
    {
        Item entiy = new Item();
        return Response.success(entiy);
    }

    @PostMapping("/create")
    public Response create(@RequestBody Item enity)
    {
        return Response.success();
    }

    @PutMapping("/update/{id}")
    public Response edit(@RequestBody Item enity, @PathVariable int id)
    {
        return Response.success();
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable int id)
    {
        return Response.success();
    }

}
