package com.binla.bcs.model.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetPageUserReqModel extends GetUserReqModel{
    private int pageStart;
    private int pageLength;

    public GetPageUserReqModel(String condition, Integer role, String orderColumn,
                               String orderType, int pageIndex, int pageSize){
        super(condition,role,orderColumn,orderType);
        this.pageStart = pageIndex;
        this.pageLength = pageSize;
    }
}
