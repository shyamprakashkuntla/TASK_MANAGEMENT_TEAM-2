package com.ve.task_management.service;


import com.ve.task_management.payload.request.TaskRequest;
import com.ve.task_management.payload.response.TaskResponse;

import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);
    TaskResponse getTaskById(Integer id);
    List<TaskResponse> getAllTasks();
    TaskResponse updateTask(Integer id, TaskRequest request);
    String deleteTask(Integer id);
}

