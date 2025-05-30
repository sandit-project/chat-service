package com.example.chatservice.service;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.dto.ChatMessageResponseDTO;
import com.example.chatservice.mapper.ChatMessageMapper;
import com.example.chatservice.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    // 채팅방 내 메시지 조회
    public List<ChatMessageResponseDTO> getMessagesByRoomId(String roomId) {
        List<ChatMessage> messages = chatMessageRepository.findByRoomIdOrderByCreatedAtAsc(roomId);
        return ChatMessageMapper.toResponseList(messages);
    }
}
