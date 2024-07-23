package com.example.messenger.service.impl;

import com.example.messenger.model.Chat;
import com.example.messenger.model.Message;
import com.example.messenger.model.User;
import com.example.messenger.repository.ChatRepository;
import com.example.messenger.repository.MessageRepository;
import com.example.messenger.repository.UserRepository;
import com.example.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Override
    public Message sendMessage(Long senderId, Long recipientId, Long chatId, String content) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chat ID"));

        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setChat(chat);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    @Override
    public List<String> showChat(Long id) {
        List<String> messages = new ArrayList<>();
        messages.add(messageRepository.getAllByChatId(id).toString());
        return messages;
    }
}
