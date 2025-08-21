package com.ve.task_management.mapper;

import com.ve.task_management.entity.Users;
import com.ve.task_management.payload.request.UserRequest;
import com.ve.task_management.payload.response.UserResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-25T11:49:50+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse userToUserResponse(Users user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setUserId( user.getUserId() );
        userResponse.setUserName( user.getUserName() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setMobile( user.getMobile() );
        userResponse.setActive( user.isActive() );

        return userResponse;
    }

    @Override
    public Users userRequestToUser(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        Users users = new Users();

        users.setUserName( request.getUserName() );
        users.setEmail( request.getEmail() );
        users.setMobile( request.getMobile() );
        users.setPassword( request.getPassword() );
        users.setActive( request.isActive() );
        users.setRemarks( request.getRemarks() );

        return users;
    }

    @Override
    public void updateUserFromRequest(UserRequest request, Users user) {
        if ( request == null ) {
            return;
        }

        if ( request.getUserName() != null ) {
            user.setUserName( request.getUserName() );
        }
        if ( request.getEmail() != null ) {
            user.setEmail( request.getEmail() );
        }
        if ( request.getMobile() != null ) {
            user.setMobile( request.getMobile() );
        }
        if ( request.getPassword() != null ) {
            user.setPassword( request.getPassword() );
        }
        user.setActive( request.isActive() );
        if ( request.getRemarks() != null ) {
            user.setRemarks( request.getRemarks() );
        }
    }
}
