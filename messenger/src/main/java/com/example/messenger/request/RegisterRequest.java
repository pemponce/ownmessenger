package com.example.messenger.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterRequest {
    private String login;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String dateOfBirth;
    private int age;
    private boolean sex;
}
