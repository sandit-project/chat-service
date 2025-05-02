package com.example.chatservice.ttlTest;

import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class TTLTestDataLoader implements CommandLineRunner {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public void run(String... args) {
        // 현재 시간 기준으로 11분 전 (660초 전)으로 설정
        Date expiredDate = new Date(System.currentTimeMillis() - (660 * 1000L)); // 11분

        ChatMessage message = new ChatMessage();
        message.setId(UUID.randomUUID().toString());
        message.setRoomId("ttl-test-room");
        message.setSender("tester");
        message.setMessage("이 메시지는 10분 TTL 테스트입니다.");
        message.setCreatedAt(expiredDate);

        chatMessageRepository.save(message);
        System.out.println("✅ TTL 테스트 메시지 저장 완료 (10분 이상 경과된 createdAt 값)");
    }
}
