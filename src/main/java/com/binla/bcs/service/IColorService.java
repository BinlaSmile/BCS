package com.binla.bcs.service;

import java.util.List;

public interface IColorService {
    List<String> getAllCode();

    void add(String code);
    void edit(String codeOld,String codeNew);
    void delete(String code);
}
