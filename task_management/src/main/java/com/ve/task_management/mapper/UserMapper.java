package com.ve.task_management.mapper;


import com.ve.task_management.entity.Users;
import com.ve.task_management.payload.request.UserRequest;
import com.ve.task_management.payload.response.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse userToUserResponse(Users user);

    Users userRequestToUser(UserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "userId", ignore = true)
    void updateUserFromRequest(UserRequest request, @MappingTarget Users user);
}

