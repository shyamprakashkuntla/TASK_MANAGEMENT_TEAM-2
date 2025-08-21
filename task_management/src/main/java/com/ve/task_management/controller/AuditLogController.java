package com.ve.task_management.controller;

import com.ve.task_management.payload.response.AuditLogResponse;
import com.ve.task_management.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    /**
     * Get all audit logs.
     */
    @GetMapping
    public List<AuditLogResponse> getAllLogs() {
        return auditLogService.getAll();
    }

    /**
     * Filter logs by username.
     */
    @GetMapping("/filter/user")
    public List<AuditLogResponse> filterByUser(@RequestParam String user) {
        return auditLogService.filterByUser(user);
    }

    /**
     * Filter logs by operation type (e.g., CREATE, UPDATE, DELETE).
     */
    @GetMapping("/filter/operation")
    public List<AuditLogResponse> filterByOperation(@RequestParam String operation) {
        return auditLogService.filterByOperation(operation);
    }

    /**
     * Filter logs within a date-time range.
     */
    @GetMapping("/filter/dates")
    public List<AuditLogResponse> filterByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return auditLogService.filterByDateRange(start, end);
    }
}
