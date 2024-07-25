package com.example.messenger.service.impl;

import com.example.messenger.dto.MessageDto;
import com.example.messenger.mapper.MessageMapper;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper messageMapper;

    @Override
    public MessageDto sendMessage(Long senderId, Long recipientId, Long chatId, String content) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chat ID"));

        Message message = Message.builder()
                .sender(sender)
                .recipient(recipient)
                .content(content)
                .timestamp(LocalDateTime.now())
                .chat(chat)
                .build();

        messageRepository.save(message);

        return messageMapper.toDto(message);
    }

    @Override
    public List<Message> showChat(Long id) {

        return messageRepository.getAllByChatId(id);
    }
}
