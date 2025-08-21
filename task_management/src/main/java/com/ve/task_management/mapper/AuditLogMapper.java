package com.ve.task_management.mapper;


import com.ve.task_management.entity.AuditLog;
import com.ve.task_management.payload.response.AuditLogResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

    @Mapping(source = "user.userName", target = "userName")
    AuditLogResponse toResponse(AuditLog auditLog);

}


