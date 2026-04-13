package com.auth_service.controller;

import com.auth_service.dto.ChangePasswordRequest;
import com.auth_service.dto.ProfileDTO;
import com.auth_service.dto.UpdateProfileRequest;
import com.auth_service.entity.User;
import com.auth_service.security.JwtUtil;
import com.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedUser = authService.login(user.getEmail(), user.getPassword());
        if (loggedUser != null) {
            return jwtUtil.generateToken(loggedUser.getEmail());
        }
        return "Invalid Credentials";
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        String email = extractEmailFromHeader(authHeader);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or missing token"));
        }

        User user = authService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
        }

        ProfileDTO profile = new ProfileDTO(
                user.getId(),
                user.getName() != null ? user.getName() : "",
                user.getEmail() != null ? user.getEmail() : "",
                user.getPhone() != null ? user.getPhone() : "",
                user.getRole() != null ? user.getRole() : "MANAGER",
                "The Golden Platter"
        );

        return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody UpdateProfileRequest request) {
        String email = extractEmailFromHeader(authHeader);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or missing token"));
        }

        User updated = authService.updateProfile(email, request);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "User not found"));
        }

        ProfileDTO profile = new ProfileDTO(
                updated.getId(),
                updated.getName() != null ? updated.getName() : "",
                updated.getEmail() != null ? updated.getEmail() : "",
                updated.getPhone() != null ? updated.getPhone() : "",
                updated.getRole() != null ? updated.getRole() : "MANAGER",
                "The Golden Platter"
        );

        return ResponseEntity.ok(profile);
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody ChangePasswordRequest request) {
        String email = extractEmailFromHeader(authHeader);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or missing token"));
        }

        if (request.getCurrentPassword() == null || request.getNewPassword() == null
                || request.getNewPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Current password and new password are required"));
        }

        boolean success = authService.changePassword(email, request);
        if (!success) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Current password is incorrect"));
        }

        return ResponseEntity.ok(Map.of("message", "Password changed successfully"));
    }

    private String extractEmailFromHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        String token = authHeader.substring(7);
        try {
            if (!jwtUtil.validateToken(token)) {
                return null;
            }
            return jwtUtil.extractEmail(token);
        } catch (Exception e) {
            return null;
        }
    }
}
