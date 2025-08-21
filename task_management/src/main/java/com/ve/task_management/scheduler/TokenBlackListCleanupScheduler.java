package com.ve.task_management.scheduler;


import com.ve.task_management.repository.TokenBlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TokenBlackListCleanupScheduler {

    private final TokenBlackListRepository tokenBlackListRepository;

    @Scheduled(cron = "0 0 * * * *") // Every hour
    public void cleanExpiredTokens() {
        tokenBlackListRepository.deleteByExpiresAtBefore(LocalDateTime.now());
        System.out.println("✅ Cleaned up expired blacklist tokens");
    }
}

