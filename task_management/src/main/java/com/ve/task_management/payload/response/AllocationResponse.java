package com.ve.task_management.payload.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AllocationResponse {

    private Integer allocationId;

    private String userName;

    private String projectName;

    private String taskName;

    private String roleInTask;

    private LocalDateTime allocatedOn;

//    private String remarks;
}
