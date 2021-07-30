package com.binla.bcs.model.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String code;
    private String name;
    private int role;
    private String pic;
    private String color;
}
