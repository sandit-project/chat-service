package com.example.chatservice.service;

import com.example.chatservice.domain.ChatRoom;
import com.example.chatservice.domain.ChatRoomReadStatus;
import com.example.chatservice.dto.ChatRoomResponseDTO;
import com.example.chatservice.mapper.ChatRoomMapper;
import com.example.chatservice.repository.ChatMessageRepository;
import com.example.chatservice.repository.ChatRoomReadStatusRepository;
import com.example.chatservice.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomReadStatusRepository chatRoomReadStatusRepository;

    // 채팅방 목록 조회
    public List<ChatRoomResponseDTO> getAllRooms() {
        List<ChatRoom> rooms = chatRoomRepository.findAll();
        return ChatRoomMapper.toResponseList(rooms);
    }

    // 채팅방 생성
    public ChatRoomResponseDTO createRoom(String name, String ownerId) {
        ChatRoom room = new ChatRoom(null, name, LocalDateTime.now(), ownerId);
        ChatRoom savedRoom = chatRoomRepository.save(room);
        return ChatRoomMapper.toResponse(savedRoom);
    }

    // 채팅방 삭제
    public void deleteRoom(String roomId) {
        chatMessageRepository.deleteByRoomId(roomId); // 메시지 삭제
        chatRoomReadStatusRepository.deleteByRoomId(roomId); // 읽음 상태 삭제
        chatRoomRepository.deleteById(roomId); // 방 삭제
    }

    /**
     * 채팅방 읽음 처리
     * 해당 userId가 roomId 채팅방을 읽은 상태로 업데이트 (없으면 생성)
     */
    @Transactional
    public void markRoomAsRead(String roomId, String userId) {
        ChatRoomReadStatus status = chatRoomReadStatusRepository.findByRoomIdAndUserId(roomId, userId)
                .orElseGet(() -> {
                    ChatRoomReadStatus newStatus = new ChatRoomReadStatus();
                    newStatus.setRoomId(roomId);
                    newStatus.setUserId(userId);
                    return newStatus;
                });
        status.setRead(true);
        status.setLastReadAt(LocalDateTime.now());
        chatRoomReadStatusRepository.save(status);
    }

    /**
     * 해당 사용자가 특정 채팅방을 읽었는지 여부 확인
     */
    public Boolean isRoomReadByUser(String roomId, String userId) {
        return chatRoomReadStatusRepository.findByRoomIdAndUserId(roomId, userId)
                .map(ChatRoomReadStatus::isRead)
                .orElse(false); // 없으면 false 반환 (읽지 않은 상태)
    }
}
