package com.example.messenger.dto;

import com.example.messenger.model.Role;
import lombok.Data;

@Data
public class UserDto {
    private String login;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String dateOfBirth;
    private boolean sex;
    private Role role;
}
