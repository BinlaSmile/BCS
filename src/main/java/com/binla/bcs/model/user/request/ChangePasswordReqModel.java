package com.binla.bcs.model.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ChangePasswordReqModel {
    @NotBlank(message = "密码不能为空")
    private String oldPassword;
    @NotBlank(message = "密码不能为空")
    private String newPassword;
}
