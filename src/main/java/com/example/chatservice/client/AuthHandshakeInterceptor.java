package com.example.chatservice.client;

import com.example.chatservice.dto.ValidTokenRequestDTO;
import com.example.chatservice.dto.ValidTokenResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {

    private final AuthFeignClient authFeignClient;

    public AuthHandshakeInterceptor(AuthFeignClient authFeignClient) {
        this.authFeignClient = authFeignClient;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String token = request.getHeaders().getFirst("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);  // "Bearer " 제외한 토큰 부분

            // Feign Client를 통해 토큰 검증 요청
            ValidTokenRequestDTO requestDTO = new ValidTokenRequestDTO(token);
            ResponseEntity<ValidTokenResponseDTO> result = authFeignClient.validToken(requestDTO);

            if (result.getStatusCode().is2xxSuccessful() && result.getBody().isValid()) {
                String role = result.getBody().getRole();
                attributes.put("role", role);  // 역할을 세션에 저장
                return true;
            }
        }

        return false;  // 인증 실패 시 연결 거부
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
    }
}
