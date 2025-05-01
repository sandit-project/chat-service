package com.example.chatservice.controller;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.dto.ChatMessageRequest;
import com.example.chatservice.dto.ChatMessageResponse;
import com.example.chatservice.mapper.ChatMessageMapper;
import com.example.chatservice.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessageRequest request) {
        // 1. 요청 DTO → 엔티티로 변환
        ChatMessage message = ChatMessageMapper.toEntity(request);

        // 2. 저장
        chatMessageRepository.save(message);

        // 3. 응답 DTO로 변환하여 전송
        ChatMessageResponse response = ChatMessageMapper.toResponse(message);

        messagingTemplate.convertAndSend("/topic/room/" + message.getRoomId(), response);
    }
}
