package com.example.chatservice.repository;

import com.example.chatservice.domain.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    boolean existsByName(String name);
}