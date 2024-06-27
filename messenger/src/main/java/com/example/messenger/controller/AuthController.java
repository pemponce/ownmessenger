package com.example.messenger.controller;

import com.example.messenger.dto.JwtAuthenticationResponse;
import com.example.messenger.dto.UserDto;
import com.example.messenger.model.Role;
import com.example.messenger.model.User;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.dto.LoginRequest;
import com.example.messenger.service.JwtService;
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
    @Autowired
    private JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository, JwtService jwtService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest.getLogin(), loginRequest.getPassword());
        if (isAuthenticated) {
            var user = userService
                    .userDetailsService().loadUserByUsername(loginRequest.getLogin());

            var jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponse(jwt);
        } else {
            throw new IllegalStateException("Incorrect password or login");
        }
    }

    @PostMapping("/register")
    public JwtAuthenticationResponse register(@RequestBody UserDto newUser) {
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepository.existsByLogin(newUser.getLogin())) {
            throw new IllegalStateException("User with this login already exists");
        }

        User user = User.builder()
                .login(newUser.getLogin())
                .fullName(newUser.getFullName())
                .password(newUser.getPassword())
                .dateOfBirth(newUser.getDateOfBirth())
                .sex(newUser.isSex())
                .role(Role.USER)
                .build();

        userService.saveUser(user);
        String jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt);
    }

}
