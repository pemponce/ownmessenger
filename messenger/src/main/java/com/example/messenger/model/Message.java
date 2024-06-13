package com.example.messenger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Users sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Users recipient;

    private String content;
    private LocalDateTime timestamp;
    @Column(name = "chat_id")
    private Long chatId;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<MediaFile> mediaFiles = new ArrayList<>();

}
