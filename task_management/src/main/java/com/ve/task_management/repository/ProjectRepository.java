package com.ve.task_management.repository;

import com.ve.task_management.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Projects,Integer> {

    @Query("SELECT c from Projects c where c.deleted=false")
    List<Projects> findAllActiveProjects();

    @Query("SELECT c from Projects c where c.projectId=:projectId and c.deleted=false")
    Optional<Projects> findActiveProjectsById(Integer projectId);
}
