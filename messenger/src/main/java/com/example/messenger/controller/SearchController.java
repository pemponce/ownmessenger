package com.example.messenger.controller;

import com.example.messenger.dto.FriendsDto;
import com.example.messenger.model.AccountDetails;
import com.example.messenger.model.Friends;
import com.example.messenger.model.User;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
            return userService.getAllUsers();
        } else {
            return userService.findByFullNameContainingOrLoginContaining(text);
        }
    }

    @PostMapping("/add_friend")
    public FriendsDto addUser(@RequestHeader("Authorization") @RequestParam String friendUrlId) {

        Long id = Long.parseLong(friendUrlId);
        User friend = userService.findUserById(id);

        return userService.addFriend(friend);
    }

    @GetMapping("/friends")
    public List<AccountDetails> myFriends(@RequestHeader("Authorization") @RequestParam Long userId) {

        List<User> users = userService.findFriendsByUserId(userId);
        List<AccountDetails> friends = new ArrayList<>();

        for (User user : users) {
            friends.add(userService.accountInfo(user));
        }

        return friends;
    }
}
