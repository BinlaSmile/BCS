package com.binla.bcs.service;

import com.binla.bcs.model.project.ProjectModel;
import com.binla.bcs.domain.Page;

public interface IProjectService {
    Page<ProjectModel> getPageList();
}
