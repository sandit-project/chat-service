package com.example.chatservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("chat_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    @Id
    private String id;

    private String roomId;
    private String sender;

    // 역할(예: ADMIN, USER 등)
    private String senderRole;

    // 타입(예: social, 일반 등)
    private String senderType;

    private String message;


    @Builder.Default
    private Date createdAt = new Date();
}
