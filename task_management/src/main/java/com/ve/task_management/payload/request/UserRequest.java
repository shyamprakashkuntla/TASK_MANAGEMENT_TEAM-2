package com.ve.task_management.payload.request;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String email;
    private String mobile;
    private String password;
    private boolean active;
    private String remarks;
}

