package com.example.chatservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // 메시지 발행 경로
        config.setApplicationDestinationPrefixes("/app"); // 메시지 수신 경로
        config.setUserDestinationPrefix("/user");      // 개인 메시지 prefix
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // 웹소켓 연결 엔드포인트
                .setAllowedOrigins("https://himedia-a.com", "http://himedia-a.com", "http://ws.himedia-a.com", "https://ws.himedia-a.com")
                .withSockJS(); // SockJS fallback

        // 배달 위치용
        registry.addEndpoint("/delivery-location")
                .setAllowedOrigins("https://himedia-a.com", "http://himedia-a.com", "http://ws.himedia-a.com", "https://ws.himedia-a.com")
                .withSockJS();
    }
}
