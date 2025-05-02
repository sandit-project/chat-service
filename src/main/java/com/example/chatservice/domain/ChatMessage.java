package com.example.chatservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("chat_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime timestamp;
    private Date createdAt = new Date();


}
