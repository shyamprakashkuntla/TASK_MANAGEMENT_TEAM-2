package com.ve.task_management.payload.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectResponse {
    private Integer projectId;
    private String projectName;
    private String clientName;  // mapped from Clients entity
    private String priority;
    private Integer progressPercent;
    private LocalDate startDate;
    private LocalDate endDate;
}

