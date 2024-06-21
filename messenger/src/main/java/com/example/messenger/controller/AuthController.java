package com.example.messenger.controller;

import com.example.messenger.model.Users;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.request.LoginRequest;
import com.example.messenger.request.RegisterRequest;
import com.example.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid Login or Password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return ResponseEntity.status(400).body("Passwords don't match");
        }

        Users user = new Users();
        user.setLogin(registerRequest.getLogin());
        user.setFullName(registerRequest.getFullName());
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setSex(registerRequest.isSex());
        user.setPassword(registerRequest.getPassword());

        if (userRepository.findAllByLogin(registerRequest.getLogin()).stream()
                .noneMatch(x -> registerRequest.getLogin().equals(x.getLogin()))
        ) {
            userService.saveUser(user);
            userRepository.save(user);
        } else {
            return ResponseEntity.status(400).body("This login already in use");
        }

        return ResponseEntity.status(200).body("Registration successful");
    }
}
