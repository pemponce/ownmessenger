package com.example.messenger.service;

import com.example.messenger.model.AccountDetails;
import com.example.messenger.model.Role;
import com.example.messenger.model.User;
import com.example.messenger.repository.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public List<User> findUsernameByFIO(String fio) {
        return userRepository.findUsernameByFIO("%" + fio + "%");
    }

    public List<User>  findUsernameByLogin(String login) {
        return userRepository.findUsernameByLogin(login);
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
