package com.ve.task_management.payload.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuditLogResponse {
    private Integer logId;
    private String operation;
    private String entity;
    private Integer entityId;
    private String requestIp;
    private LocalDateTime createdAt;
    private String details;
    private String userName;
}

