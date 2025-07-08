package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userDao = new UserDao();
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String registerUser(User user) {
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.save(user);
        return "User registered successfully";
    }

    public String loginUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authentication);
            // return "User logged in successfully";
        } else {
            return "Invalid username or password";
        }
    }
}
