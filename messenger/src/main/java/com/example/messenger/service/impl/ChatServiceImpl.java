package com.example.messenger.service.impl;

import com.example.messenger.dto.ChatDto;
import com.example.messenger.model.Chat;
import com.example.messenger.model.User;
import com.example.messenger.repository.ChatRepository;
import com.example.messenger.service.ChatService;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;


    @Override
    public List<Chat> getAllChats(Long userId) {
        return chatRepository.getAllByUserId(userId);
    }

    @Override
    public Chat showChat(Long  id) {
        return chatRepository.getChatById(id);
    }

    public Chat createChat(Long friendId) {
        User currentUser = userService.getCurrentUser();
        User friend = userService.findUserById(friendId);

        Chat chat = Chat.builder()
                .users(Arrays.asList(currentUser, friend))
                .messages(null)
                .isGroup(false)
                .build();

        return chatRepository.save(chat);
    }

    public Object deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
        return null;
    }
}
