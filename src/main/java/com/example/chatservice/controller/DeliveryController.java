package com.example.chatservice.controller;

import com.example.chatservice.dto.DeliveryLocationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {
    @MessageMapping("/delivery/update-location") // 클라이언트 -> 서버
    @SendTo("/topic/delivery-location") // 서버 -> 구독자
    public DeliveryLocationDTO handleLocationUpdate(DeliveryLocationDTO location) {
        // 여기에 로직이 있으면 가공 가능
        return location; // 이 데이터가 /topic/delivery-location 로 브로드캐스팅됨
    }
}
