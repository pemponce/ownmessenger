package com.example.messenger.repository;

import com.example.messenger.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUsers_Id(Long userId);
    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.Id = :userId")
    List<Chat> getAllByUserId(Long userId);
    Chat getChatById(Long chatId);
    boolean existsByUsers_IdInAndUsers_IdIn(List<Long> userIds1, List<Long> userIds2);
    Optional<Chat> findByUsers_IdInAndUsers_IdIn(List<Long> userIds1, List<Long> userIds2);
}
