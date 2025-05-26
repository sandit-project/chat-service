package com.example.chatservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomReadStatusResponseDTO {
    private String roomId;
    private String userId;
    private boolean read;
    private LocalDateTime lastReadAt;
}
