package com.ve.task_management.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data //generates getters, setters, to string, hashcode, equals
@AllArgsConstructor // generates all args constructor with all fields
@NoArgsConstructor //generates no arg constructor
public class ApiResponse<T> {
    private Integer statusCode;
    private String message;
    private boolean success;
    private T data;


    public ApiResponse(HttpStatus statusCode, String message, boolean success, T data)
    {
        this.statusCode = statusCode.value();
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public ApiResponse(HttpStatus statusCode, boolean success, String message)
    {
        this.statusCode = statusCode.value();
        this.success = success;
        this.message = message;
    }


}
