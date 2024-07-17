package com.example.messenger.controller;

import com.example.messenger.dto.FriendsDto;
import com.example.messenger.model.User;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class SearchController {
    private final UserService userService;

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam String text) {
        if (text == null || text.isBlank()) {
            System.out.println("sex 1");
            return userService.getAllUsers();
        } else {
            System.out.println("sex 2");
            return userService.findByFullNameContainingOrLoginContaining(text);
        }
    }

    @PostMapping("/add_friend")
    public FriendsDto addUser(@RequestHeader("Authorization") @RequestParam String friendUrlId) {
        System.out.println("sex 1");

        Long id = Long.parseLong(friendUrlId);
        System.out.println("sex 2");

        User friend = userService.findUserById(id);
        System.out.println("sex 3");

        return userService.addFriend(friend);
    }
}
