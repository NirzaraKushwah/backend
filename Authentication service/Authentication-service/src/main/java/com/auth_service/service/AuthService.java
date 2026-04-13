package com.auth_service.service;

import com.auth_service.dto.ChangePasswordRequest;
import com.auth_service.dto.UpdateProfileRequest;
import com.auth_service.entity.User;
import com.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateProfile(String email, UpdateProfileRequest req) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        if (req.getName() != null && !req.getName().isBlank()) {
            user.setName(req.getName());
        }
        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            user.setEmail(req.getEmail());
        }
        if (req.getPhone() != null) {
            user.setPhone(req.getPhone());
        }
        return userRepository.save(user);
    }

    public boolean changePassword(String email, ChangePasswordRequest req) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        if (!user.getPassword().equals(req.getCurrentPassword())) {
            return false;
        }
        user.setPassword(req.getNewPassword());
        userRepository.save(user);
        return true;
    }
}