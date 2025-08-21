package com.ve.task_management.service;


import com.ve.task_management.payload.request.ProjectRequest;
import com.ve.task_management.payload.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request);
    ProjectResponse getProjectById(Integer id);
    List<ProjectResponse> getAllProjects();
    ProjectResponse updateProject(Integer id, ProjectRequest request);
    String deleteProject(Integer id);
}

