package com.binla.bcs.service.impl;

import com.binla.bcs.entity.DicColor;
import com.binla.bcs.entity.User;
import com.binla.bcs.repository.IDicColorRepository;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.service.IColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ColorService implements IColorService {

    @Autowired
    private IDicColorRepository colorRepository;

    @Autowired
    private IAuthService authService;

    @Override
    public List<String> getAllCode() {
        var colorList = colorRepository.getAll().stream().map(DicColor::getColorCode).collect(Collectors.toList());
        return colorList;
    }

    @Override
    public void add(String code) {
        var currentUserCode =authService.getCurrentUserCode();
        var dateNow = new Date();

        var color = new DicColor();
        color.setColorCode(code);
        color.setInsertUser(currentUserCode);
        color.setInsertDate(dateNow);
        color.setUpdateUser(currentUserCode);
        color.setUpdateDate(dateNow);
        colorRepository.insert(color);
    }

    @Override
    public void edit(String codeOld, String codeNew) {
        var currentUserCode =authService.getCurrentUserCode();
        var dateNow = new Date();

        var color = colorRepository.getByCode(codeOld);
        color.setColorCode(codeNew);
        color.setUpdateDate(dateNow);
        color.setUpdateUser(currentUserCode);
        colorRepository.update(codeOld,color);
    }

    @Override
    public void delete(String code) {
        colorRepository.delete(code);
    }
}
