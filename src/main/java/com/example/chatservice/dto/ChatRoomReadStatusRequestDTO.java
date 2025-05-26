package com.example.chatservice.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomReadStatusRequestDTO {
    private String roomId;
    private String userId;
}
