package com.example.messenger.controller;

import com.example.messenger.model.User;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@CrossOrigin
public class SearchController {
    private final UserService userService;

    @GetMapping("search")
    public User searchUser(@RequestBody String text) {
        if (userService.findUsernameByFIO(text).isEmpty()) {
            List<User> users = userService.findUsernameByLogin(text);
            return users.get(0);
        } else {
            List<User> users = userService.findUsernameByFIO(text);
            return users.get(0);
        }
    }
}
