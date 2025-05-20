package com.example.chatservice.repository;

import com.example.chatservice.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    // createdAt 필드 기준 오름차순 정렬로 변경
    List<ChatMessage> findByRoomIdOrderByCreatedAtAsc(String roomId);

    void deleteByRoomId(String roomId);
}

