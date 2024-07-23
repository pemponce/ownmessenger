package com.example.messenger.repository;

import com.example.messenger.dto.MessageDto;
import com.example.messenger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> getAllByChatId(Long chatId);
}
