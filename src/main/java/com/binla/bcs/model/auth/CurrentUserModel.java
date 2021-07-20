package com.binla.bcs.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserModel {
    private String code;
    private String name;
    private int role;
    private String roleName;
    private String pic;
    private String color;
}
