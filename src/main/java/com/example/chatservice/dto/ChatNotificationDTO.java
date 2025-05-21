package com.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatNotificationDTO {
    private String type;       // "NEW_MESSAGE"
    private String roomId;     // 어떤 채팅방에서 왔는지
    private int unreadCount;   // 읽지 않은 메시지 수 (간단하게 1로 고정도 가능)
}
