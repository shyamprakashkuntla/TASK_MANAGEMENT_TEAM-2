package com.ve.task_management.mapper;

import com.ve.task_management.entity.Clients;
import com.ve.task_management.entity.Projects;
import com.ve.task_management.payload.request.ProjectRequest;
import com.ve.task_management.payload.response.ProjectResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-25T11:49:50+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectResponse projectToProjectResponse(Projects project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponse projectResponse = new ProjectResponse();

        projectResponse.setClientName( projectClientClientName( project ) );
        projectResponse.setProjectId( project.getProjectId() );
        projectResponse.setProjectName( project.getProjectName() );
        projectResponse.setPriority( project.getPriority() );
        projectResponse.setProgressPercent( project.getProgressPercent() );
        projectResponse.setStartDate( project.getStartDate() );
        projectResponse.setEndDate( project.getEndDate() );

        return projectResponse;
    }

    @Override
    public Projects projectRequestToProject(ProjectRequest request) {
        if ( request == null ) {
            return null;
        }

        Projects projects = new Projects();

        projects.setProjectName( request.getProjectName() );
        projects.setPriority( request.getPriority() );
        projects.setStartDate( request.getStartDate() );
        projects.setEndDate( request.getEndDate() );
        projects.setRemarks( request.getRemarks() );

        return projects;
    }

    @Override
    public void updateProjectFromRequest(ProjectRequest request, Projects project) {
        if ( request == null ) {
            return;
        }

        if ( request.getProjectName() != null ) {
            project.setProjectName( request.getProjectName() );
        }
        if ( request.getPriority() != null ) {
            project.setPriority( request.getPriority() );
        }
        if ( request.getStartDate() != null ) {
            project.setStartDate( request.getStartDate() );
        }
        if ( request.getEndDate() != null ) {
            project.setEndDate( request.getEndDate() );
        }
        if ( request.getRemarks() != null ) {
            project.setRemarks( request.getRemarks() );
        }
    }

    private String projectClientClientName(Projects projects) {
        if ( projects == null ) {
            return null;
        }
        Clients client = projects.getClient();
        if ( client == null ) {
            return null;
        }
        String clientName = client.getClientName();
        if ( clientName == null ) {
            return null;
        }
        return clientName;
    }
}
