package com.binla.bcs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryCondition {

    //limit起始
    private int pageStart = 0;

    //limit条数
    private int pageLength = 10;

    //需要orderBy的列名
    private String orderColumn;

    //ASC DESC
    private String orderType = "ASC";
}
