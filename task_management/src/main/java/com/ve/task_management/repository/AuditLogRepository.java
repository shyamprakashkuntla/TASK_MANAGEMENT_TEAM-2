package com.ve.task_management.repository;

import com.ve.task_management.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {

    List<AuditLog> findByUser_UserName(String userName);

    List<AuditLog> findByOperation(String operation);

    List<AuditLog> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}


