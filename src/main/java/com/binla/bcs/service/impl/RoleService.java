package com.binla.bcs.service.impl;

import com.binla.bcs.model.role.response.RoleModel;
import com.binla.bcs.repository.ILogRepository;
import com.binla.bcs.repository.IRoleRepository;
import com.binla.bcs.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ILogRepository logRepository;

    public List<RoleModel> getAll(){
        var result = new ArrayList<RoleModel>();
        var entityList = roleRepository.getAll();
        if(entityList!=null&&entityList.size()>0){
            for(var item : entityList){
                result.add(new RoleModel(item.getId(), item.getName(), item.getDesc()));
            }
        }
        return result;
    }
}
