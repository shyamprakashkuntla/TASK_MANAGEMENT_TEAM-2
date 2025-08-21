package com.ve.task_management.repository;

import com.ve.task_management.entity.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    // ✅ Check only if blacklist entry is still valid
    boolean existsByJtiAndExpiresAtAfter(String jti, LocalDateTime now);

    // ✅ For cleanup
    void deleteByExpiresAtBefore(LocalDateTime now);
}
