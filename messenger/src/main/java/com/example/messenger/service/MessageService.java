package com.example.messenger.service;

import com.example.messenger.dto.MessageDto;
import com.example.messenger.model.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(Long senderId, Long recipientId, Long chatId, String content);
    List<Message> showChat(Long id);

}
