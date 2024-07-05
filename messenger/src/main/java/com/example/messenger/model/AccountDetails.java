package com.example.messenger.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDetails {
    private String login;
    private String fullName;
    private String dateOfBirth;
    private boolean sex;
}
