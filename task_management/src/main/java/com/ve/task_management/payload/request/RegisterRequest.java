package com.ve.task_management.payload.request;


import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    // Add other fields if needed
}

