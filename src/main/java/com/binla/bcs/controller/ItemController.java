package com.binla.bcs.controller;

import com.binla.bcs.domain.Item;
import com.binla.bcs.model.common.ResponseModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Item")
@RequestMapping("/api/item")
public class ItemController {

    @GetMapping("/getList")
    public ResponseModel getList()
    {
        return ResponseModel.Success();
    }

    @GetMapping("/getInfo/{id}")
    public ResponseModel getInfo(@PathVariable int id)
    {
        Item entiy = new Item();
        return ResponseModel.Success(entiy);
    }

    @PostMapping("/create")
    public ResponseModel create(@RequestBody Item enity)
    {
        return ResponseModel.Success();
    }

    @PutMapping("/update/{id}")
    public ResponseModel edit(@RequestBody Item enity, @PathVariable int id)
    {
        return ResponseModel.Success();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel delete(@PathVariable int id)
    {
        return ResponseModel.Success();
    }

}
