package com.example.messenger.repository;

import com.example.messenger.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
}
