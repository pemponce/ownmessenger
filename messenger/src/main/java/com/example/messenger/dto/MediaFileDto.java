package com.example.messenger.dto;

import com.example.messenger.model.Message;

import lombok.Data;

@Data
public class MediaFileDto {
    private String fileName;
    private String fileType;
    private String url;

    private Message message;
}
