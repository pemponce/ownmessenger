package com.example.messenger.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
