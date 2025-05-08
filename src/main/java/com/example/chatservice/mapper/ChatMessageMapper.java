package com.example.chatservice.mapper;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.dto.ChatMessageRequestDTO;
import com.example.chatservice.dto.ChatMessageResponseDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChatMessageMapper {

    // ChatMessage 엔티티 → ChatMessageResponse 변환
    public static ChatMessageResponseDTO toResponse(ChatMessage message) {
        return new ChatMessageResponseDTO(
                message.getId(),
                message.getRoomId(),
                message.getSender(),
                message.getMessage(),
                message.getTimestamp()
        );
    }

    // ChatMessage 엔티티 리스트 → ChatMessageResponse 리스트 변환
    public static List<ChatMessageResponseDTO> toResponseList(List<ChatMessage> messages) {
        return messages.stream()
                .map(ChatMessageMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ChatMessageRequest → ChatMessage 엔티티 변환
    public static ChatMessage toEntity(ChatMessageRequestDTO request) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date createdAt = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return new ChatMessage(
                null, // ID는 MongoDB에서 자동 생성
                request.getRoomId(),
                request.getSender(),
                request.getMessage(),
                LocalDateTime.now(), // 현재 시간으로 타임스탬프 설정
                createdAt
        );
    }
}
