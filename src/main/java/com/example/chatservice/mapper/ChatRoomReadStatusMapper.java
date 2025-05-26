package com.example.chatservice.mapper;

import com.example.chatservice.domain.ChatRoomReadStatus;
import com.example.chatservice.dto.ChatRoomReadStatusRequestDTO;
import com.example.chatservice.dto.ChatRoomReadStatusResponseDTO;

public class ChatRoomReadStatusMapper {

    public static ChatRoomReadStatusResponseDTO toResponseDTO(ChatRoomReadStatus entity) {
        if (entity == null) return null;

        return ChatRoomReadStatusResponseDTO.builder()
                .roomId(entity.getRoomId())
                .userId(entity.getUserId())
                .read(entity.isRead())
                .lastReadAt(entity.getLastReadAt())
                .build();
    }

    public static ChatRoomReadStatus toEntity(ChatRoomReadStatusRequestDTO dto) {
        if (dto == null) return null;

        return ChatRoomReadStatus.builder()
                .roomId(dto.getRoomId())
                .userId(dto.getUserId())
                .read(true)  // 기본 읽음 상태 true로 설정
                .lastReadAt(java.time.LocalDateTime.now())
                .build();
    }
}
