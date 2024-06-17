package com.example.messenger.controller;

import com.example.messenger.model.MediaFile;
import com.example.messenger.service.MediaFileService;
import com.example.messenger.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/messages")
@CrossOrigin
public class MessageController {
    private final MessageService messageService;
    private final MediaFileService mediaFileService;

    public MessageController(MessageService messageService, MediaFileService mediaFileService) {
        this.messageService = messageService;
        this.mediaFileService = mediaFileService;
    }

    @PostMapping("/{messageId}/upload")
    public ResponseEntity<?> uploadFiles(@PathVariable Long messageId, @RequestParam("files") MultipartFile[] files) {
        try {
            List<MediaFile> mediaFiles = mediaFileService.saveFiles(messageId, files);
            return ResponseEntity.ok(mediaFiles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file(s)");
        }
    }
}
