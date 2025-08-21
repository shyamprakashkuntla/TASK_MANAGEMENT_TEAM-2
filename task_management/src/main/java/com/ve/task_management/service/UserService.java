package com.ve.task_management.service;


import com.ve.task_management.payload.request.UserRequest;
import com.ve.task_management.payload.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUserById(Integer id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Integer id, UserRequest request);
    String deleteUser(Integer id);
    UserResponse getUserByEmail(String email);

}

