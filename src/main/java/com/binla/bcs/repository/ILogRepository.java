package com.binla.bcs.repository;

import com.binla.bcs.domain.QueryCondition;
import com.binla.bcs.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ILogRepository {

    int getPageCount(String condition, Integer type, Date beginDate, Date endDate);

    List<Log> getPageList(String condition, Integer type, Date beginDate, Date endDate, @Param("query")QueryCondition query);

    void insert(@Param("log")Log log);
}