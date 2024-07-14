package com.example.messenger.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchAccountDetails {
    private String fullName;
    private String login;
    private boolean sex;
}
