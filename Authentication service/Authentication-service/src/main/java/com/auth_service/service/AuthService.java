package com.auth_service.service;



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

    public User login(String email,String password) {

        User user=userRepository.findByEmail(email);

        if(user!=null && user.getPassword().equals(password)) {

            return user;
        }

        return null;
    }

}