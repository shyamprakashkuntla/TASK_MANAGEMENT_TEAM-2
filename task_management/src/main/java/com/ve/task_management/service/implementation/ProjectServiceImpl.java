package com.ve.task_management.service.implementation;


import com.ve.task_management.entity.Clients;
import com.ve.task_management.entity.Projects;
import com.ve.task_management.mapper.ProjectMapper;
import com.ve.task_management.payload.request.ProjectRequest;
import com.ve.task_management.payload.response.ProjectResponse;
import com.ve.task_management.repository.ClientRepository;
import com.ve.task_management.repository.ProjectRepository;
import com.ve.task_management.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;  // needed for setting client entity
    private final ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Projects project = projectMapper.projectRequestToProject(request);

        Clients client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        project.setClient(client);
        project.setProgressPercent(0); // default progress

        Projects savedProject = projectRepository.save(project);
        return projectMapper.projectToProjectResponse(savedProject);
    }

    @Override
    public ProjectResponse getProjectById(Integer id) {
        Projects project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found of id: "+ id));
        return projectMapper.projectToProjectResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::projectToProjectResponse)
                .toList();
    }

    @Override
    public ProjectResponse updateProject(Integer id, ProjectRequest request) {
        Projects project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        projectMapper.updateProjectFromRequest(request, project);

        if (request.getClientId() != null) {
            Clients client = clientRepository.findById(request.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            project.setClient(client);
        }

        Projects updatedProject = projectRepository.save(project);
        return projectMapper.projectToProjectResponse(updatedProject);
    }

    @Override
    public String deleteProject(Integer id) {
        Projects project = projectRepository.findActiveProjectsById(id)
                .orElseThrow(()-> new RuntimeException("project not found of id "+id));
        project.setDeleted(true);
        return "project deleted successfully";
    }
}

