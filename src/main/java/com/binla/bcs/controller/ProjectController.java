package com.binla.bcs.controller;

import com.binla.bcs.domain.Project;
import com.binla.bcs.model.common.PageReqModel;
import com.binla.bcs.model.common.ResponseModel;
import com.binla.bcs.model.project.request.GetProjectListReqModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Project")
@RequestMapping("/api/project")
public class ProjectController {

    @GetMapping("/getList")
    public ResponseModel getList(@RequestBody PageReqModel pageInfo,@RequestBody GetProjectListReqModel reqModel)
    {

        return ResponseModel.Success();
    }

    @GetMapping("/getInfo/{id}")
    public ResponseModel getInfo(@PathVariable int id)
    {
        Project entiy = new Project();
        return ResponseModel.Success(entiy);
    }

    @PostMapping("/create")
    public ResponseModel create(@RequestBody Project enity)
    {
        return ResponseModel.Success();
    }

    @PutMapping("/update/{id}")
    public ResponseModel edit(@RequestBody Project enity, @PathVariable int id)
    {
        return ResponseModel.Success();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel delete(@PathVariable int id)
    {
        return ResponseModel.Success();
    }
}
