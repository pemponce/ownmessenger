package com.example.messenger.repository;

import com.example.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByLogin(String login);
    User findByLogin(String login);
    boolean existsByLogin(String login);
}