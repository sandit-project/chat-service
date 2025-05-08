package com.example.chatservice.mapper;

import com.example.chatservice.domain.ChatRoom;
import com.example.chatservice.dto.ChatRoomRequestDTO;
import com.example.chatservice.dto.ChatRoomResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ChatRoomMapper {

    // ChatRoom 엔티티 → ChatRoomResponse 변환
    public static ChatRoomResponseDTO toResponse(ChatRoom room) {
        return new ChatRoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getCreatedAt()
        );
    }

    // ChatRoom 엔티티 리스트 → ChatRoomResponse 리스트 변환
    public static List<ChatRoomResponseDTO> toResponseList(List<ChatRoom> rooms) {
        return rooms.stream()
                .map(ChatRoomMapper::toResponse)
                .collect(Collectors.toList());
    }

    // ChatRoomRequest → ChatRoom 엔티티 변환
    public static ChatRoom toEntity(ChatRoomRequestDTO request) {
        return new ChatRoom(
                null, // ID는 MongoDB에서 자동 생성
                request.getName(),
                null // 생성 시간은 컨트롤러에서 현재 시간으로 설정할 수 있음
        );
    }
}
