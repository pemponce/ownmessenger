package com.example.messenger.repository;

import com.example.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLogin(String login);
    User findByLogin(String login);
    User findByFullName(String fullName);
    @Query("SELECT users.login FROM User users")
    List<User> getAllUsers();
    @Query("SELECT users FROM User users WHERE users.fullName LIKE %:text% OR users.login LIKE %:text%")
    List<User> findByFullNameContainingOrLoginContaining(@Param("text") String text);
    boolean existsByLogin(String login);
}