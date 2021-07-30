package com.binla.bcs.model.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateUserReqModel {
    @NotBlank(message = "用户代码不能为空")
    private String code;
    @NotBlank(message = "用户名不能为空")
    private String name;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Min(value = 0,message = "用户角色信息有误")
    private int role;
    private String pic;
    private String color;
}
