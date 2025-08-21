package com.ve.task_management.payload.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {
    private Integer taskId;
    private String taskName;
    private String projectName;   // mapped from Projects entity
    private LocalDate taskDate;
    private String status;
    private Integer percentComplete;
}

