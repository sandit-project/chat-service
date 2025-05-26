package com.example.chatservice.repository;

import com.example.chatservice.domain.ChatRoomReadStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomReadStatusRepository extends MongoRepository<ChatRoomReadStatus, Long> {

    Optional<ChatRoomReadStatus> findByRoomIdAndUserId(String roomId, String userId);

    void deleteByRoomId(String roomId);

}
