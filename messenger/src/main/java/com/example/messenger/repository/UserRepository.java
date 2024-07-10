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
    @Query("SELECT users.login FROM User users WHERE users.fullName LIKE :text")
    List<User> findUsernameByFIO(@Param("text") String text);
    @Query("SELECT users.login FROM User users WHERE users.login LIKE :text")
    List<User> findUsernameByLogin(@Param("text") String text);
    boolean existsByLogin(String login);
}