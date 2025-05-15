package com.example.chatservice.controller;

import com.example.chatservice.dto.DeliveryLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/delivery/update-location") // 클라이언트 → 서버
    public void handleLocationUpdate(DeliveryLocationDTO location) {
        String topic = "/topic/delivery-location/" + location.getMerchantUid(); // 동적 토픽 생성
        messagingTemplate.convertAndSend(topic, location); // 해당 토픽으로 전송
    }
}
