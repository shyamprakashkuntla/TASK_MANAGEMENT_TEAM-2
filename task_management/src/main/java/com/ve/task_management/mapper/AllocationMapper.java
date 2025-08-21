package com.ve.task_management.mapper;

import com.ve.task_management.entity.Allocations;
import com.ve.task_management.payload.request.AllocationRequest;
import com.ve.task_management.payload.response.AllocationResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AllocationMapper {

    @Mapping(source = "user.userName", target = "userName")
    @Mapping(source = "project.projectName", target = "projectName")
    @Mapping(source = "task.taskName", target = "taskName")
    AllocationResponse allocationToAllocationResponse(Allocations allocation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "allocationId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "task", ignore = true)
    void updateAllocationFromRequest(AllocationRequest request, @MappingTarget Allocations allocation);

    @Mapping(target = "allocationId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "task", ignore = true)
    Allocations allocationRequestToAllocation(AllocationRequest request);
}
