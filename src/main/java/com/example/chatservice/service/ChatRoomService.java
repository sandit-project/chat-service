package com.example.chatservice.service;

import com.example.chatservice.domain.ChatRoom;
import com.example.chatservice.dto.ChatRoomResponseDTO;
import com.example.chatservice.mapper.ChatRoomMapper;
import com.example.chatservice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    // 채팅방 목록 조회
    public List<ChatRoomResponseDTO> getAllRooms() {
        List<ChatRoom> rooms = chatRoomRepository.findAll();
        return ChatRoomMapper.toResponseList(rooms);
    }

    // 채팅방 생성
    public ChatRoomResponseDTO createRoom(String name) {
        ChatRoom room = new ChatRoom(null, name, LocalDateTime.now());
        ChatRoom savedRoom = chatRoomRepository.save(room);
        return ChatRoomMapper.toResponse(savedRoom);
    }
}
