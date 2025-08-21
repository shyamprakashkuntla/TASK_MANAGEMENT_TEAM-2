package com.ve.task_management.aspect;

import com.ve.task_management.entity.AuditLog;
import com.ve.task_management.entity.Users;
import com.ve.task_management.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;
    private final HttpServletRequest request;

    // POINTCUTS
    @Pointcut("execution(* com.ve.task_management.service.implementation.*.create*(..))")
    public void createOperation() {}

    @Pointcut("execution(* com.ve.task_management.service.implementation.*.update*(..))")
    public void updateOperation() {}

    @Pointcut("execution(* com.ve.task_management.service.implementation.*.delete*(..))")
    public void deleteOperation() {}

    // ADVICE: Create
    @AfterReturning(pointcut = "createOperation()", returning = "result")
    public void logCreate(JoinPoint joinPoint, Object result) {
        saveAuditLog(joinPoint, "Create", result);
    }

    // ADVICE: Update
    @AfterReturning(pointcut = "updateOperation()", returning = "result")
    public void logUpdate(JoinPoint joinPoint, Object result) {
        saveAuditLog(joinPoint, "Update", result);
    }

    // ADVICE: Delete
    @AfterReturning(pointcut = "deleteOperation()", returning = "result")
    public void logDelete(JoinPoint joinPoint, Object result) {
        saveAuditLog(joinPoint, "Delete", result);
    }

    // CORE AUDIT LOGIC
    private void saveAuditLog(JoinPoint joinPoint, String operation, Object result) {
        try {
            String entity = joinPoint.getTarget().getClass().getSimpleName().replace("ServiceImpl", "");
            String ipAddress = request.getRemoteAddr();

            // Get authenticated user (null if not set up yet)
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Users user = null;
            if (authentication != null && authentication.getPrincipal() instanceof Users) {
                user = (Users) authentication.getPrincipal();
            }

            Integer entityId = extractEntityId(result);

            AuditLog log = new AuditLog();
            log.setOperation(operation);
            log.setEntity(entity);
            log.setRequestIp(ipAddress);
            log.setUser(user);
            log.setEntityId(entityId);
            log.setDetails(operation + " operation performed on " + entity +
                    (entityId != null ? " [ID: " + entityId + "]" : ""));

            auditLogRepository.save(log);

//            log.("✅ Audit log saved: {} - {} - ID: {}", entity, operation, entityId);

        } catch (Exception e) {
            log.error("❌ Failed to save audit log", e);
        }
    }

    // Extracts the ID dynamically (first 'getId*' method found)
    private Integer extractEntityId(Object result) {
        if (result == null) {
            return null;
        }
        try {
            for (Method method : result.getClass().getMethods()) {
                String name = method.getName().toLowerCase();
                if (name.startsWith("get") && name.endsWith("id") && method.getParameterCount() == 0) {
                    Object value = method.invoke(result);
                    if (value instanceof Integer) {
                        return (Integer) value;
                    } else if (value instanceof Long) {
                        return ((Long) value).intValue();
                    }
                }
            }
        } catch (Exception e) {
            log.warn(" Could not extract entity ID", e);
        }
        return null;
    }

}
