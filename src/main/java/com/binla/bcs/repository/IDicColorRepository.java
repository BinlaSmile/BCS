package com.binla.bcs.repository;

import com.binla.bcs.domain.QueryCondition;
import com.binla.bcs.entity.DicColor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDicColorRepository {
    DicColor getByCode(String code);
    List<DicColor> getAll();

    int getPageCount(String condition);
    List<DicColor> getPageList(String condition, @Param("query") QueryCondition query);

    void insert(@Param("color")DicColor color);
    void insertBatch(@Param("colorList")List<DicColor> colorList);
    void update(String code,@Param("color")DicColor color);
    void delete(String code);
    void deleteBatch(@Param("codeList")List<String> codeList);
}
