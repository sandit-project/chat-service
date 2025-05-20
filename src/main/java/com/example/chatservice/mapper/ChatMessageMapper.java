package com.example.chatservice.mapper;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.dto.ChatMessageRequestDTO;
import com.example.chatservice.dto.ChatMessageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ChatMessageMapper {

    // ChatMessage 엔티티 → ChatMessageResponseDTO 변환
    public static ChatMessageResponseDTO toResponse(ChatMessage message) {
        return ChatMessageResponseDTO.builder()
                .id(message.getId())
                .roomId(message.getRoomId())
                .sender(message.getSender())
                .senderRole(message.getSenderRole())
                .senderType(message.getSenderType())
                .message(message.getMessage())
                .build();
    }

    // ChatMessage 엔티티 리스트 → ChatMessageResponseDTO 리스트 변환
    public static List<ChatMessageResponseDTO> toResponseList(List<ChatMessage> messages) {
        return messages.stream()
                .map(ChatMessageMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ChatMessageRequestDTO → ChatMessage 엔티티 변환
    public static ChatMessage toEntity(ChatMessageRequestDTO request) {
        return ChatMessage.builder()
                .roomId(request.getRoomId())
                .sender(request.getSender())
                .senderRole(request.getSenderRole())
                .senderType(request.getSenderType())
                .message(request.getMessage())
                // createdAt는 @Builder.Default로 자동 설정됨
                .build();
    }
}
