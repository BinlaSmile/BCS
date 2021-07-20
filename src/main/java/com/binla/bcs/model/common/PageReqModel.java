package com.binla.bcs.model.common;

import lombok.Data;

@Data
public class PageReqModel {

    //Datatables 第多少次请求
    private int draw;

    //当前第几页
    private int pageIndex = 0;

    //当前页多数条数据
    private int pageSize = 0;

    public int getPage() {
        if (pageSize == 0) {
            return 1;
        }
        int page = pageIndex / pageSize;
        page += 1;
        return page;
    }
}
