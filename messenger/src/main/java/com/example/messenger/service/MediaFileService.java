package com.example.messenger.service;

import com.example.messenger.model.MediaFile;
import com.example.messenger.model.Message;
import com.example.messenger.repository.MediaFileRepository;
import com.example.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MediaFileService {
    private final MediaFileRepository mediaFileRepository;
    private final MessageRepository messageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public MediaFileService(MediaFileRepository mediaFileRepository, MessageRepository messageRepository) {
        this.mediaFileRepository = mediaFileRepository;
        this.messageRepository = messageRepository;
    }

    public List<MediaFile> saveFiles(Long messageId, MultipartFile[] files) throws IOException {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new IllegalArgumentException("Invalid message ID"));

        List<MediaFile> mediaFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            MediaFile mediaFile = new MediaFile();
            mediaFile.setFileName(filename);
            mediaFile.setFileType(file.getContentType());
            mediaFile.setUrl(filePath.toString());
            mediaFile.setMessage(message);

            mediaFiles.add(mediaFileRepository.save(mediaFile));
        }
        return mediaFiles;
    }
}
