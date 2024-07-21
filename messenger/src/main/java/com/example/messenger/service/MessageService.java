package com.example.messenger.service;

import com.example.messenger.model.Message;

public interface MessageService {
    Message sendMessage(Long senderId, Long recipientId, Long chatId, String content);
}
