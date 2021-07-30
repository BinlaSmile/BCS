package com.binla.bcs.model.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserReqModel {
    private String condition;
    private Integer role;
    private String orderColumn;
    private String orderType;
}
