package com.ve.task_management.mapper;


import com.ve.task_management.entity.Tasks;
import com.ve.task_management.payload.request.TaskRequest;
import com.ve.task_management.payload.response.TaskResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "project.projectName", target = "projectName")
    TaskResponse taskToTaskResponse(Tasks task);

    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "project", ignore = true)  // handled in service
    Tasks taskRequestToTask(TaskRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "taskId", ignore = true)
    @Mapping(target = "project", ignore = true)
    void updateTaskFromRequest(TaskRequest request, @MappingTarget Tasks task);
}

