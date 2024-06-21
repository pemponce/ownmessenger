package com.example.messenger.repository;

import com.example.messenger.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findAllByLogin(String login);

    Users findByLogin(String login);
}