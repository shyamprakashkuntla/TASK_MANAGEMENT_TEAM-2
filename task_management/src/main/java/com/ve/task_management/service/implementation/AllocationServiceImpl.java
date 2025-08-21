package com.ve.task_management.service.implementation;

import com.ve.task_management.entity.Allocations;
import com.ve.task_management.entity.Projects;
import com.ve.task_management.entity.Tasks;
import com.ve.task_management.entity.Users;
import com.ve.task_management.mapper.AllocationMapper;
import com.ve.task_management.payload.request.AllocationRequest;
import com.ve.task_management.payload.response.AllocationResponse;
import com.ve.task_management.repository.AllocationRepository;
import com.ve.task_management.repository.ProjectRepository;
import com.ve.task_management.repository.TaskRepository;
import com.ve.task_management.repository.UserRepository;
import com.ve.task_management.service.AllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllocationServiceImpl implements AllocationService {

    private final AllocationRepository allocationRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final AllocationMapper allocationMapper;

    @Override
    public AllocationResponse createAllocation(AllocationRequest request) {
        if (allocationRepository.allocationExists(
                request.getUserId(), request.getProjectId(), request.getTaskId())) {
            throw new RuntimeException("This allocation already exists for the user.");
        }
        Allocations allocation = allocationMapper.allocationRequestToAllocation(request);

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Projects project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Tasks task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        allocation.setUser(user);
        allocation.setProject(project);
        allocation.setTask(task);

        Allocations saved = allocationRepository.save(allocation);
        return allocationMapper.allocationToAllocationResponse(saved);
    }

    @Override
    public AllocationResponse updateAllocation(Integer id, AllocationRequest request) {
        Allocations allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));

        allocationMapper.updateAllocationFromRequest(request, allocation);

        Users user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Projects project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        Tasks task = taskRepository.findById(request.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        allocation.setUser(user);
        allocation.setProject(project);
        allocation.setTask(task);

        Allocations updated = allocationRepository.save(allocation);
        return allocationMapper.allocationToAllocationResponse(updated);
    }

    @Override
    public AllocationResponse getAllocationById(Integer id) {
        Allocations allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
        return allocationMapper.allocationToAllocationResponse(allocation);
    }

    @Override
    public List<AllocationResponse> getAllAllocations() {
        return allocationRepository.findAll().stream()
                .map(allocationMapper::allocationToAllocationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteAllocationById(Integer id) {
        Allocations existingAllocations = allocationRepository.findActiveAllocationById(id)
                .orElseThrow(()-> new RuntimeException("allocation not found of id "+ id));
         existingAllocations.setDeleted(true);

         return "allocation deleted successfully";
    }
}
