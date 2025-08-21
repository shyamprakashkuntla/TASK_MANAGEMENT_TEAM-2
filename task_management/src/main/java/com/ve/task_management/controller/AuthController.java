package com.ve.task_management.controller;

import com.ve.task_management.config.JwtService;
import com.ve.task_management.entity.Users;
import com.ve.task_management.payload.request.RegisterRequest;
import com.ve.task_management.payload.response.AuthResponse;
import com.ve.task_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.getEmail().trim())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        Users user = new Users();
        user.setUserName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);

        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/admin/create-admin")
    @PreAuthorize("hasRole('ADMIN')") // ✅ Only admins
    public ResponseEntity<?> createAdmin(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.getEmail().trim())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        Users admin = new Users();
        admin.setUserName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole("ADMIN");

        userRepository.save(admin);

        return ResponseEntity.ok("Admin created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Users user = userRepository.findByEmailIgnoreCase(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Not found"));

        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
    }
}
