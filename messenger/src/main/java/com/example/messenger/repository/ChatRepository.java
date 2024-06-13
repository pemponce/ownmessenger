package com.example.messenger.repository;

import com.example.messenger.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUsers_Id(Long userId);
}
