package com.ve.task_management.service.implementation;

import com.ve.task_management.entity.TokenBlackList;
import com.ve.task_management.entity.Users;
import com.ve.task_management.repository.TokenBlackListRepository;
import com.ve.task_management.repository.UserRepository;
import com.ve.task_management.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {

    private final UserRepository userRepository;
    private final TokenBlackListRepository tokenBlackListRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Value("${lockout.max.attempts}")
    private int maxAttempts;

    @Value("${lockout.duration.minutes}")
    private int lockDurationMinutes;

    public void changePassword(String email, String currentPassword, String newPassword, String confirmPassword) {

        Users user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("New passwords do not match");
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new RuntimeException("New password must be different from current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    /**
     * ✅ This method adds the token to blacklist until its natural expiry.
     */
    public void logout(String token) {
        String jti = jwtService.extractJti(token);
        LocalDateTime expiresAt = jwtService.extractExpiration(token)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        TokenBlackList blacklist = new TokenBlackList();
        blacklist.setJti(jti);
        blacklist.setExpiresAt(expiresAt);

        tokenBlackListRepository.save(blacklist);

        System.out.println("Token blacklisted until: " + expiresAt);
    }


    public void login(String email, String password) {
        Users user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ 1) Check if user is locked
        if (user.getAccountLockedUntil() != null && user.getAccountLockedUntil().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Account is locked until: " + user.getAccountLockedUntil());
        }

        // ✅ 2) If password matches → reset failed attempts
        if (passwordEncoder.matches(password, user.getPassword())) {
            user.setFailedAttempts(0);
            user.setAccountLockedUntil(null); // unlock if locked
            userRepository.save(user);
            return; // success
        }

        // ✅ 3) If wrong password → increment failed attempts
        int attempts = user.getFailedAttempts() != null ? user.getFailedAttempts() : 0;
        attempts++;
        user.setFailedAttempts(attempts);

        // ✅ 4) If max reached → lock user
        if (attempts >= maxAttempts) {
            user.setAccountLockedUntil(LocalDateTime.now().plusMinutes(lockDurationMinutes));
            user.setFailedAttempts(0); // reset after lock
        }

        userRepository.save(user);
        throw new RuntimeException("Invalid password. Attempts: " + attempts);
    }

}
