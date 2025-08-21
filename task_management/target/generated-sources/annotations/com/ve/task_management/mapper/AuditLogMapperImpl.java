package com.ve.task_management.mapper;

import com.ve.task_management.entity.AuditLog;
import com.ve.task_management.entity.Users;
import com.ve.task_management.payload.response.AuditLogResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-25T11:49:50+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class AuditLogMapperImpl implements AuditLogMapper {

    @Override
    public AuditLogResponse toResponse(AuditLog auditLog) {
        if ( auditLog == null ) {
            return null;
        }

        AuditLogResponse auditLogResponse = new AuditLogResponse();

        auditLogResponse.setUserName( auditLogUserUserName( auditLog ) );
        auditLogResponse.setLogId( auditLog.getLogId() );
        auditLogResponse.setOperation( auditLog.getOperation() );
        auditLogResponse.setEntity( auditLog.getEntity() );
        auditLogResponse.setEntityId( auditLog.getEntityId() );
        auditLogResponse.setRequestIp( auditLog.getRequestIp() );
        auditLogResponse.setCreatedAt( auditLog.getCreatedAt() );
        auditLogResponse.setDetails( auditLog.getDetails() );

        return auditLogResponse;
    }

    private String auditLogUserUserName(AuditLog auditLog) {
        if ( auditLog == null ) {
            return null;
        }
        Users user = auditLog.getUser();
        if ( user == null ) {
            return null;
        }
        String userName = user.getUserName();
        if ( userName == null ) {
            return null;
        }
        return userName;
    }
}
