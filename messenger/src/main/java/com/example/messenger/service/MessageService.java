package com.example.messenger.service;

import com.example.messenger.model.MediaFile;
import com.example.messenger.model.Message;
import com.example.messenger.model.User;
import com.example.messenger.repository.MessageRepository;
import com.example.messenger.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message sendMessage(Long senderId, Long recipientId, String content, List<MultipartFile> files) throws IOException {
        User sender = userRepository.findById(senderId).orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
        User recipient = userRepository.findById(recipientId).orElseThrow(() -> new IllegalArgumentException("Invalid recipient ID"));

        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                MediaFile mediaFile = saveMediaFile(savedMessage, file);
                savedMessage.getMediaFiles().add(mediaFile);
            }
            savedMessage = messageRepository.save(savedMessage);
        }

        return savedMessage;
    }

    private MediaFile saveMediaFile(Message message, MultipartFile file) throws IOException {
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get("uploads", filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileName(filename);
        mediaFile.setFileType(file.getContentType());
        mediaFile.setUrl(filePath.toString());
        mediaFile.setMessage(message);

        return mediaFile;
    }
}
