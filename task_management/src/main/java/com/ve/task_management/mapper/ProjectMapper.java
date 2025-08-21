package com.ve.task_management.mapper;


import com.ve.task_management.entity.Projects;
import com.ve.task_management.payload.request.ProjectRequest;
import com.ve.task_management.payload.response.ProjectResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(source = "client.clientName", target = "clientName")
    ProjectResponse projectToProjectResponse(Projects project);

    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "client", ignore = true)  // handled in service
    @Mapping(target = "progressPercent", ignore = true)
    Projects projectRequestToProject(ProjectRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "projectId", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "progressPercent", ignore = true)
    void updateProjectFromRequest(ProjectRequest request, @MappingTarget Projects project);
}

