package com.ve.task_management.repository;

import com.ve.task_management.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks,Integer> {
    @Query("SELECT c from Tasks c where c.deleted=false")
    List<Tasks> findAllActiveTasks();

    @Query("SELECT c from Tasks c where c.taskId=:taskId and c.deleted=false")
    Optional<Tasks> findActiveTasksById(Integer taskId);
}
