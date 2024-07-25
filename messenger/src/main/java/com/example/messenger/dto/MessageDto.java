package com.example.messenger.dto;

import com.example.messenger.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class MessageDto {
    private String sender;
    private String recipient;
    private String content;
    private LocalDateTime timestamp;
    private Long chatId;

}
