package com.example.chatservice.controller;

import com.example.chatservice.domain.ChatRoom;
import com.example.chatservice.dto.ChatMessageResponseDTO;
import com.example.chatservice.dto.ChatRoomRequestDTO;
import com.example.chatservice.dto.ChatRoomResponseDTO;
import com.example.chatservice.service.ChatRoomService;
import com.example.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    // 채팅방 생성 API
    @PostMapping("/rooms")
    public ResponseEntity<ChatRoomResponseDTO> createRoom(@RequestBody ChatRoomRequestDTO request) {
        ChatRoomResponseDTO room= chatRoomService.createRoom(request.getName(), request.getOwnerId());
        return ResponseEntity.ok(room);
    }

    // 채팅방 목록 조회 API
    @GetMapping("/rooms")
    public List<ChatRoomResponseDTO> listRooms() {
        return chatRoomService.getAllRooms();
    }

    // 채팅방 내 메시지 기록 조회 API
    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessageResponseDTO> getMessages(@PathVariable String roomId) {
        return chatMessageService.getMessagesByRoomId(roomId);
    }

    // 채팅방 삭제 API 추가
    @DeleteMapping("/rooms/{roomId}")
    public void deleteRoom(@PathVariable String roomId) {
        chatRoomService.deleteRoom(roomId);
    }

}
