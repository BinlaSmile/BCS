package com.binla.bcs.service;

import com.binla.bcs.model.project.ProjectModel;
import com.binla.bcs.model.common.Page;

public interface IProjectService {
    Page<ProjectModel> getPageList();
}
