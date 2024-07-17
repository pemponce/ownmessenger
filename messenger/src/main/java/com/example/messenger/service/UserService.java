package com.example.messenger.service;

import com.example.messenger.dto.FriendsDto;
import com.example.messenger.model.*;
import com.example.messenger.repository.FriendsRepository;
import com.example.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FriendsRepository friendsRepository;

    public User save(User user) {

        return userRepository.save(user);
    }
    public AccountDetails accountInfo(User user) {

        return AccountDetails.builder()
                .login(user.getLogin())
                .fullName(user.getFullName())
                .sex(user.isSex())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public FriendsDto addFriend(User friend) {

        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("User must be logged in to add a friend");
        }
        if (currentUser.getId().equals(friend.getId())) {
            throw new IllegalArgumentException("Нельзя добавить себя в друзья");
        }

        if (!userRepository.existsById(currentUser.getId()) || !userRepository.existsById(friend.getId())) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        if (friendsRepository.existsByUserIdAndFriendId(currentUser.getId(), friend.getId())) {
            throw new IllegalArgumentException("Этот пользователь уже в списке друзей");
        }

        Friends newFriendship = Friends.builder()
                .userId(currentUser.getId())
                .friendId(friend.getId())
                .build();

        friendsRepository.save(newFriendship);

        return FriendsDto.builder()
                .userId(currentUser.getId())
                .friendId(friend.getId())
                .build();
    }

    public boolean authenticate(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }

    public User create(User user) {
        if (userRepository.existsByLogin(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        return save(user);
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public List<User> findByFullNameContainingOrLoginContaining(String text) {

        return userRepository.findByFullNameContainingOrLoginContaining(text);
    }
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public UserDetailsService userDetailsService() {
        return this::getByLogin;
    }

    public User getCurrentUser() {
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(login);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

}
