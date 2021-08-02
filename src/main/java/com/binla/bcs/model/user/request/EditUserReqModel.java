package com.binla.bcs.model.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EditUserReqModel {
    @NotBlank(message = "用户名不能为空")
    private String name;
    @Min(value = 0,message = "用户角色信息有误")
    private int role;
    private String pic;
    private String color;
}
