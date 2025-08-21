package com.ve.task_management.service;



import com.ve.task_management.payload.response.AuditLogResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditLogService {
    List<AuditLogResponse> getAll();
    List<AuditLogResponse> filterByUser(String userName);
    List<AuditLogResponse> filterByOperation(String operation);
    List<AuditLogResponse> filterByDateRange(LocalDateTime start, LocalDateTime end);
}


