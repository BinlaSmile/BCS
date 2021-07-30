package com.binla.bcs.model.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EditUserReqModel {
    private String name;
    private int role;
    private String pic;
    private String color;
}
