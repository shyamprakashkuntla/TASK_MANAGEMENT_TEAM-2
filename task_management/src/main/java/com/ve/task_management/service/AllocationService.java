package com.ve.task_management.service;

import com.ve.task_management.payload.request.AllocationRequest;
import com.ve.task_management.payload.response.AllocationResponse;

import java.util.List;

public interface AllocationService {
    AllocationResponse createAllocation(AllocationRequest request);
    AllocationResponse updateAllocation(Integer id, AllocationRequest request);
    AllocationResponse getAllocationById(Integer id);
    List<AllocationResponse> getAllAllocations();
    String deleteAllocationById(Integer id);
}
