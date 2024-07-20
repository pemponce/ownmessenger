package com.example.messenger.service;

import com.example.messenger.dto.FriendsDto;
import com.example.messenger.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    User save(User user);
    AccountDetails accountInfo(User user);

    FriendsDto addFriend(User friend);

    List<User> findFriendsByUserId();

    boolean authenticate(String login, String password);

    void create(User user);

    User getByLogin(String login);
    List<User> getAllUsers();

    List<User> findByFullNameContainingOrLoginContaining(String text);
    User findUserById(Long id);

    UserDetailsService userDetailsService();

    User getCurrentUser();
    void getAdmin();

}
