package com.example.messenger.service;

import com.example.messenger.model.Message;
import com.example.messenger.model.User;
import com.example.messenger.repository.MessageRepository;
import com.example.messenger.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long recipientId, String content, List<MultipartFile> files) throws IOException;
}
