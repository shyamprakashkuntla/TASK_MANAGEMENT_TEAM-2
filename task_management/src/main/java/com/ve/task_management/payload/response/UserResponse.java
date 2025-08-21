package com.ve.task_management.payload.response;


import lombok.Data;

@Data
public class UserResponse {
    private Integer userId;
    private String userName;
    private String email;
    private String mobile;
    private boolean active;
}
