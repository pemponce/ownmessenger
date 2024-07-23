package com.example.messenger.service;

import com.example.messenger.model.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> getAllChats(Long userId);
    Chat createChat(Long friendId);
    Object deleteChat(Long chatId);
}
