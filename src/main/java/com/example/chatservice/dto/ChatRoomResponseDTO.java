package com.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomResponseDTO {
    private String id;
    private String name;

    private LocalDateTime createdAt;
    private String ownerId;  // 응답에 포함해서 프론트에서 확인 가능하도록 추가


}
