package com.example.chatservice.redis;


import com.example.chatservice.domain.ChatMessage;
import com.example.chatservice.repository.ChatMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisSubscriber implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;
    private final ChatMessageRepository chatMessageRepository;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String body = new String(message.getBody());
            ChatMessage chatMessage = objectMapper.readValue(body, ChatMessage.class);
            chatMessageRepository.save(chatMessage);
            messagingTemplate.convertAndSend("/topic/" + chatMessage.getRoomId(), chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
