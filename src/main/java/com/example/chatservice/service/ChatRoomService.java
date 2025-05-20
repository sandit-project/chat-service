package com.example.chatservice.service;

import com.example.chatservice.domain.ChatRoom;
import com.example.chatservice.dto.ChatRoomResponseDTO;
import com.example.chatservice.mapper.ChatRoomMapper;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository; // 메시지 삭제용

    // 채팅방 목록 조회
    public List<ChatRoomResponseDTO> getAllRooms() {
        List<ChatRoom> rooms = chatRoomRepository.findAll();
        return ChatRoomMapper.toResponseList(rooms);
    }

    // 회원 ID(ownerId)를 받아 저장
    public ChatRoomResponseDTO createRoom(String name,String ownerId) {
        ChatRoom room = new ChatRoom(null, name, LocalDateTime.now(), ownerId);
        ChatRoom savedRoom = chatRoomRepository.save(room);
        return ChatRoomMapper.toResponse(savedRoom);
    }
    // 채팅방 삭제
    public void deleteRoom(String roomId) {
//         채팅방 메시지 삭제 (선택사항)
         chatMessageRepository.deleteByRoomId(roomId);

        // 채팅방 삭제
        chatRoomRepository.deleteById(roomId);
    }
}
