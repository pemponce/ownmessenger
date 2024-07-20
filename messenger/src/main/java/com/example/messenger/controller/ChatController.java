package com.example.messenger.controller;

import com.example.messenger.model.Chat;
import com.example.messenger.model.Message;
import com.example.messenger.service.ChatService;
import com.example.messenger.service.MessageService;
import com.example.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat")
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

    @GetMapping("")
    public List<Chat> chatList() {
        return chatService.getAllChats(userService.getCurrentUser().getId());
    }

    @GetMapping("/{chatId}")
    public Chat chatPage(@PathVariable Long chatId) {
        return chatService.showChat(chatId);
    }

    @PostMapping("/create")
    public Chat createChat(@RequestParam Long friendId) {
        return chatService.createChat(friendId);
    }

    @DeleteMapping("/delete/{chatId}")
    public void deleteChat(@PathVariable Long chatId) {
        chatService.deleteChat(chatId);
    }

}
