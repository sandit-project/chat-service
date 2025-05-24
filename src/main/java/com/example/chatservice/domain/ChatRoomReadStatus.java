package com.example.chatservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("chat_room_read_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomReadStatus {

    @Id
    private String id;

    private String roomId;

    private String userId;

    private boolean read; // 읽음 상태

    private LocalDateTime lastReadAt; // 마지막 읽은 시각

    // equals, hashCode, toString은 필요에 따라 추가 가능
}
