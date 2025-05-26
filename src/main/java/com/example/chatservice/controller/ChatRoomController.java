package com.example.chatservice.controller;

import com.example.chatservice.dto.ChatMessageResponseDTO;
import com.example.chatservice.dto.ChatRoomRequestDTO;
import com.example.chatservice.dto.ChatRoomResponseDTO;
import com.example.chatservice.service.ChatRoomService;
import com.example.chatservice.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
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
    public ChatRoomResponseDTO createRoom(@RequestBody ChatRoomRequestDTO request) {
        return chatRoomService.createRoom(request.getName(), request.getOwnerId());
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

    // 채팅방 읽음 처리 API 추가
    @PostMapping("/rooms/{roomId}/read")
    public void markRoomAsRead(@PathVariable String roomId, @RequestParam String userId) {
        // userId는 쿼리 파라미터로 전달하거나, 인증 정보에서 가져올 수 있음
        chatRoomService.markRoomAsRead(roomId, userId);
    }

    // 채팅방 읽음 상태 조회 API 추가
    @GetMapping("/rooms/{roomId}/read")
    public Boolean isRoomReadByUser(@PathVariable String roomId, @RequestParam String userId) {
        // chatRoomService에 읽음 상태를 조회하는 메서드 필요
        return chatRoomService.isRoomReadByUser(roomId, userId);
    }

}
