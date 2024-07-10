package com.example.messenger.controller;

import com.example.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {
    private final MessageService messageService;

}
