package com.example.chatservice.controller;

import com.example.chatservice.dto.ChatMessageResponse;
import com.example.chatservice.dto.ChatRoomRequest;
import com.example.chatservice.dto.ChatRoomResponse;
import com.example.chatservice.service.ChatRoomService;
import com.example.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    // 채팅방 생성 API
    @PostMapping("/rooms")
    public ChatRoomResponse createRoom(@RequestBody ChatRoomRequest request) {
        return chatRoomService.createRoom(request.getName());
    }

    // 채팅방 목록 조회 API
    @GetMapping("/rooms")
    public List<ChatRoomResponse> listRooms() {
        return chatRoomService.getAllRooms();
    }

    // 채팅방 내 메시지 기록 조회 API
    @GetMapping("/rooms/{roomId}/messages")
    public List<ChatMessageResponse> getMessages(@PathVariable String roomId) {
        return chatMessageService.getMessagesByRoomId(roomId);
    }
}
