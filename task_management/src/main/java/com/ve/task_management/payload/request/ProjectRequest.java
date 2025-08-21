package com.ve.task_management.payload.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectRequest {
    private Integer clientId;   // selected from dropdown
    private String projectName;
    private String priority;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remarks;
}

