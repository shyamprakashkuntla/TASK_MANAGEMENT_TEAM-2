package com.ve.task_management.mapper;

import com.ve.task_management.entity.Projects;
import com.ve.task_management.entity.Tasks;
import com.ve.task_management.payload.request.TaskRequest;
import com.ve.task_management.payload.response.TaskResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-25T11:49:50+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskResponse taskToTaskResponse(Tasks task) {
        if ( task == null ) {
            return null;
        }

        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setProjectName( taskProjectProjectName( task ) );
        taskResponse.setTaskId( task.getTaskId() );
        taskResponse.setTaskName( task.getTaskName() );
        taskResponse.setTaskDate( task.getTaskDate() );
        taskResponse.setStatus( task.getStatus() );
        taskResponse.setPercentComplete( task.getPercentComplete() );

        return taskResponse;
    }

    @Override
    public Tasks taskRequestToTask(TaskRequest request) {
        if ( request == null ) {
            return null;
        }

        Tasks tasks = new Tasks();

        tasks.setTaskDate( request.getTaskDate() );
        tasks.setTaskName( request.getTaskName() );
        tasks.setStatus( request.getStatus() );
        tasks.setPercentComplete( request.getPercentComplete() );
        tasks.setRemarks( request.getRemarks() );

        return tasks;
    }

    @Override
    public void updateTaskFromRequest(TaskRequest request, Tasks task) {
        if ( request == null ) {
            return;
        }

        if ( request.getTaskDate() != null ) {
            task.setTaskDate( request.getTaskDate() );
        }
        if ( request.getTaskName() != null ) {
            task.setTaskName( request.getTaskName() );
        }
        if ( request.getStatus() != null ) {
            task.setStatus( request.getStatus() );
        }
        if ( request.getPercentComplete() != null ) {
            task.setPercentComplete( request.getPercentComplete() );
        }
        if ( request.getRemarks() != null ) {
            task.setRemarks( request.getRemarks() );
        }
    }

    private String taskProjectProjectName(Tasks tasks) {
        if ( tasks == null ) {
            return null;
        }
        Projects project = tasks.getProject();
        if ( project == null ) {
            return null;
        }
        String projectName = project.getProjectName();
        if ( projectName == null ) {
            return null;
        }
        return projectName;
    }
}
