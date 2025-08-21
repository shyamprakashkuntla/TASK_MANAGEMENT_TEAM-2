package com.ve.task_management.service.implementation;


import com.ve.task_management.entity.Users;
import com.ve.task_management.mapper.UserMapper;
import com.ve.task_management.payload.request.UserRequest;
import com.ve.task_management.payload.response.UserResponse;
import com.ve.task_management.repository.UserRepository;
import com.ve.task_management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        Users user = userMapper.userRequestToUser(request);
        Users savedUser = userRepository.save(user);
        return userMapper.userToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Integer id, UserRequest request) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserFromRequest(request, user);
        Users updatedUser = userRepository.save(user);
        return userMapper.userToUserResponse(updatedUser);
    }

    @Override
    public String deleteUser(Integer id) {
        Users existingUser = userRepository.findActiveUsersById(id)
                .orElseThrow(()-> new RuntimeException("user not found of id "+ id));
        existingUser.setDeleted(true);
        return "user deleted successfully";
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        Users user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserResponse(user);
    }

}

