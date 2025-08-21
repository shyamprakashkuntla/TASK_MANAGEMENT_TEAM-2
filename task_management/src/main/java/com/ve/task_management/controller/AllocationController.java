package com.ve.task_management.controller;

import com.ve.task_management.payload.request.AllocationRequest;
import com.ve.task_management.payload.response.AllocationResponse;
import com.ve.task_management.service.AllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // ✅ Entire controller protected
public class AllocationController {

    private final AllocationService allocationService;

    @PostMapping
    public ResponseEntity<AllocationResponse> createAllocation(@RequestBody AllocationRequest request) {
        AllocationResponse response = allocationService.createAllocation(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AllocationResponse>> getAllAllocations() {
        List<AllocationResponse> response = allocationService.getAllAllocations();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AllocationResponse> getAllocationById(@PathVariable Integer id) {
        AllocationResponse response = allocationService.getAllocationById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AllocationResponse> updateAllocation(
            @PathVariable Integer id,
            @RequestBody AllocationRequest request) {
        AllocationResponse response = allocationService.updateAllocation(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllocation(@PathVariable Integer id) {
        allocationService.deleteAllocationById(id);
        return ResponseEntity.noContent().build();
    }
}
