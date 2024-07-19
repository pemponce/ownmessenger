package com.example.messenger.repository;

import com.example.messenger.model.Friends;
import com.example.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {
    boolean existsByUserIdAndFriendId(Long userId, Long friendId);
    @Query("SELECT u FROM User u WHERE u.Id IN (SELECT f.friendId FROM Friends f WHERE f.userId = :userId)")
    List<User> findFriendsByUserId(@Param("userId") Long userId);
}
