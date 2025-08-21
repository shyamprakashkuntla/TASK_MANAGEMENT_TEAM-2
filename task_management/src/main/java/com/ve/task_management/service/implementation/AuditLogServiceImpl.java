package com.ve.task_management.service.implementation;

import com.ve.task_management.mapper.AuditLogMapper;
import com.ve.task_management.payload.response.AuditLogResponse;
import com.ve.task_management.repository.AuditLogRepository;
import com.ve.task_management.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    @Override
    public List<AuditLogResponse> getAll() {
        return auditLogRepository.findAll().stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditLogResponse> filterByUser(String userName) {
        return auditLogRepository.findByUser_UserName(userName).stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditLogResponse> filterByOperation(String operation) {
        return auditLogRepository.findByOperation(operation).stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuditLogResponse> filterByDateRange(LocalDateTime start, LocalDateTime end) {
        return auditLogRepository.findByCreatedAtBetween(start, end).stream()
                .map(auditLogMapper::toResponse)
                .collect(Collectors.toList());
    }
}
