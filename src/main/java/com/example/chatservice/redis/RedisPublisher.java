package com.example.chatservice.redis;

import com.example.chatservice.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(String topic, ChatMessage message) {
        redisTemplate.convertAndSend(topic, message);
    }
}

