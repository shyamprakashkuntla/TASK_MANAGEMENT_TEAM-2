package com.ve.task_management.service.implementation;


import com.ve.task_management.entity.Projects;
import com.ve.task_management.entity.Tasks;
import com.ve.task_management.mapper.TaskMapper;
import com.ve.task_management.payload.request.TaskRequest;
import com.ve.task_management.payload.response.TaskResponse;
import com.ve.task_management.repository.ProjectRepository;
import com.ve.task_management.repository.TaskRepository;
import com.ve.task_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(TaskRequest request) {
        Tasks task = taskMapper.taskRequestToTask(request);

        Projects project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        task.setProject(project);

        Tasks savedTask = taskRepository.save(task);
        return taskMapper.taskToTaskResponse(savedTask);
    }

    @Override
    public TaskResponse getTaskById(Integer id) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found of id :"+ id));
        return taskMapper.taskToTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::taskToTaskResponse)
                .toList();
    }

    @Override
    public TaskResponse updateTask(Integer id, TaskRequest request) {
        Tasks task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskMapper.updateTaskFromRequest(request, task);

        if (request.getProjectId() != null) {
            Projects project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            task.setProject(project);
        }

        Tasks updatedTask = taskRepository.save(task);
        return taskMapper.taskToTaskResponse(updatedTask);
    }

    @Override
    public String deleteTask(Integer id) {
        Tasks task = taskRepository.findActiveTasksById(id)
                .orElseThrow(()-> new RuntimeException("task not found of id "+id));
        task.setDeleted(true);
        return "task successfully deleted";
    }
}

