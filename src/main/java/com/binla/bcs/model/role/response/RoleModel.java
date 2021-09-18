package com.binla.bcs.model.role.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleModel {
    private int id;
    private String name;
    private String desc;
}
