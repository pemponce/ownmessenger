package com.example.messenger.config;

import com.example.messenger.dto.JwtAuthenticationFilter;
import com.example.messenger.service.JwtService;
import com.example.messenger.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        return new JwtAuthenticationFilter(jwtService, userService);
    }
}
