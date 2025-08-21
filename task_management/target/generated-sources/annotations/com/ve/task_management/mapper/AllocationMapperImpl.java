package com.ve.task_management.mapper;

import com.ve.task_management.entity.Allocations;
import com.ve.task_management.entity.Projects;
import com.ve.task_management.entity.Tasks;
import com.ve.task_management.entity.Users;
import com.ve.task_management.payload.request.AllocationRequest;
import com.ve.task_management.payload.response.AllocationResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-25T11:49:50+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class AllocationMapperImpl implements AllocationMapper {

    @Override
    public AllocationResponse allocationToAllocationResponse(Allocations allocation) {
        if ( allocation == null ) {
            return null;
        }

        AllocationResponse allocationResponse = new AllocationResponse();

        allocationResponse.setUserName( allocationUserUserName( allocation ) );
        allocationResponse.setProjectName( allocationProjectProjectName( allocation ) );
        allocationResponse.setTaskName( allocationTaskTaskName( allocation ) );
        allocationResponse.setAllocationId( allocation.getAllocationId() );
        allocationResponse.setRoleInTask( allocation.getRoleInTask() );
        allocationResponse.setAllocatedOn( allocation.getAllocatedOn() );

        return allocationResponse;
    }

    @Override
    public void updateAllocationFromRequest(AllocationRequest request, Allocations allocation) {
        if ( request == null ) {
            return;
        }

        if ( request.getRoleInTask() != null ) {
            allocation.setRoleInTask( request.getRoleInTask() );
        }
        if ( request.getRemarks() != null ) {
            allocation.setRemarks( request.getRemarks() );
        }
    }

    @Override
    public Allocations allocationRequestToAllocation(AllocationRequest request) {
        if ( request == null ) {
            return null;
        }

        Allocations allocations = new Allocations();

        allocations.setRoleInTask( request.getRoleInTask() );
        allocations.setRemarks( request.getRemarks() );

        return allocations;
    }

    private String allocationUserUserName(Allocations allocations) {
        if ( allocations == null ) {
            return null;
        }
        Users user = allocations.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }

    private String allocationProjectProjectName(Allocations allocations) {
        if ( allocations == null ) {
            return null;
        }
        Projects project = allocations.getProject();
        if ( project == null ) {
            return null;
        }
        String projectName = project.getProjectName();
        if ( projectName == null ) {
            return null;
        }
        return projectName;
    }

    private String allocationTaskTaskName(Allocations allocations) {
        if ( allocations == null ) {
            return null;
        }
        Tasks task = allocations.getTask();
        if ( task == null ) {
            return null;
        }
        String taskName = task.getTaskName();
        if ( taskName == null ) {
            return null;
        }
        return taskName;
    }
}
