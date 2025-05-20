package com.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ChatMessageResponseDTO {
    private String id;
    private String roomId;
    private String sender;
    private String senderRole;
    private String senderType;
    private String message;
    private LocalDateTime createdAt;
}
