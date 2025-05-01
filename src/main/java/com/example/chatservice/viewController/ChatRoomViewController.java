package com.example.chatservice.viewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatRoomViewController {



    // 채팅방 목록을 반환하는 메소드
    @GetMapping("/")
    public String listChatRooms() {
        return "chat-rooms"; // templates/chat-rooms.html을 렌더링
    }
    @GetMapping("/chat-room")
    public String chatRoom() {
        return "chat-room";  // 모델 없이 'chat-room' 템플릿만 반환
    }
}
