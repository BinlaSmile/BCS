package com.binla.bcs.controller;

import com.binla.bcs.entity.Project;
import com.binla.bcs.model.common.PageReqModel;
import com.binla.bcs.domain.Response;
import com.binla.bcs.model.project.request.GetProjectListReqModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "Project")
@RequestMapping("/api/project")
public class ProjectController {

    @GetMapping("/getList")
    public Response getList(@RequestBody PageReqModel pageInfo, @RequestBody GetProjectListReqModel reqModel)
    {

        return Response.Success();
    }

    @GetMapping("/getInfo/{id}")
    public Response getInfo(@PathVariable int id)
    {
        Project entiy = new Project();
        return Response.Success(entiy);
    }

    @PostMapping("/create")
    public Response create(@RequestBody Project enity)
    {
        return Response.Success();
    }

    @PutMapping("/update/{id}")
    public Response edit(@RequestBody Project enity, @PathVariable int id)
    {
        return Response.Success();
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable int id)
    {
        return Response.Success();
    }
}
