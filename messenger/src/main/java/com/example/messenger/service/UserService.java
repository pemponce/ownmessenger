package com.example.messenger.service;

import com.example.messenger.model.Users;
import com.example.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String login, String password) {
        Users user = userRepository.findByLogin(login);
        if(user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }

    public void saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
