package com.example.messenger.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendsDto {
    private Long userId;
    private Long friendId;
}
