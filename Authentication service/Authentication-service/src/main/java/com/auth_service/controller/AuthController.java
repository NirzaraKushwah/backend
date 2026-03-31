package com.auth_service.controller;



import com.auth_service.entity.User;
import com.auth_service.security.JwtUtil;
import com.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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

        User loggedUser=authService.login(user.getEmail(),user.getPassword());

        if(loggedUser!=null) {

            return jwtUtil.generateToken(loggedUser.getEmail());
        }

        return "Invalid Credentials";
    }

}
