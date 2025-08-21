package com.ve.task_management.payload.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private Integer projectId;   // from dropdown
    private String taskName;
    private LocalDate taskDate;
    private String status;
    private Integer percentComplete;
    private String remarks;
}

