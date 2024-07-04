package com.example.messenger.controller;

import com.example.messenger.dto.JwtAuthenticationResponse;
import com.example.messenger.dto.SignUpRequest;
import com.example.messenger.model.Role;
import com.example.messenger.model.User;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.dto.SignInRequest;
import com.example.messenger.service.AuthenticationService;
import com.example.messenger.service.JwtService;
import com.example.messenger.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody @Valid SignInRequest loginRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
        if (isAuthenticated) {
            return authenticationService.signIn(loginRequest);
        } else {
            throw new IllegalStateException("Incorrect password or login");
        }
    }

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody @Valid SignUpRequest signUpRequest) {
        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepository.existsByLogin(signUpRequest.getLogin())) {
            throw new IllegalStateException("User with this login already exists");
        }

        return authenticationService.signUp(signUpRequest);
    }

}

