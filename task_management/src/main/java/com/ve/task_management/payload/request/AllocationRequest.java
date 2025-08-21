package com.ve.task_management.payload.request;

import lombok.Data;

@Data
public class AllocationRequest {
    private Integer userId;
    private Integer projectId;
    private Integer taskId;
    private String roleInTask;
    private String remarks;
}
