package com.ve.task_management.repository;

import com.ve.task_management.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailIgnoreCase(String email);  // ✅ Use IgnoreCase!
    boolean existsByEmailIgnoreCase(String email);
    // ✅ Use IgnoreCase!


    @Query("SELECT c from Users c where c.deleted=false")
    List<Users> findAllActiveUsers();

    @Query("SELECT c from Users c where c.userId=:userId and c.deleted=false")
    Optional<Users> findActiveUsersById(Integer userId);
}
