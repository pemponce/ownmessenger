package com.example.messenger.controller;

import com.example.messenger.config.JwtConfig;
import com.example.messenger.model.AccountDetails;
import com.example.messenger.model.User;
import com.example.messenger.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@CrossOrigin
public class AccountController {

    private final JwtConfig jwtConfig;
    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Доступен только авторизованным пользователям")
    public AccountDetails profile(@RequestHeader("Authorization") String authorizationHeader) throws JsonProcessingException {
        String jwt = authorizationHeader.substring(7); // Remove "Bearer " prefix
        String payload = jwtConfig.jwtDecode(jwt);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payloadMap = objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {});

        User user = userService.getByLogin((String) payloadMap.get("login"));

        return userService.accountInfo(user);
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }
}
