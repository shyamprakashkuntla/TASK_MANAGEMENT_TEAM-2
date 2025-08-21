package com.ve.task_management.repository;

import com.ve.task_management.entity.Allocations;
import com.ve.task_management.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AllocationRepository extends JpaRepository<Allocations,Integer> {
    @Query("Select e from Allocations e where e.deleted=false")
    List<Allocations> findAllActiveAllocation();

    @Query("SELECT c from Allocations c where c.allocationId=:allocationId and c.deleted=false")
    Optional<Allocations> findActiveAllocationById(Integer allocationId);

    @Query("SELECT COUNT(a) > 0 FROM Allocations a WHERE a.user.id = :userId AND a.project.id = :projectId AND a.task.id = :taskId")
    boolean allocationExists(@Param("userId") Integer userId,
                             @Param("projectId") Integer projectId,
                             @Param("taskId") Integer taskId);

}
