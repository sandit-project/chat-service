package com.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageRequestDTO {
    private String roomId;
    private String sender;
    private String senderRole;   // ADMIN, USER 등 역할
    private String senderType;   // social, normal 등 사용자 유형
    private String message;
}
