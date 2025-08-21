package com.ve.task_management.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    // Getters and Setters
}

