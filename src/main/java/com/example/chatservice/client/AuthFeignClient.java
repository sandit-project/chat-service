package com.example.chatservice.client;

import com.example.chatservice.dto.ValidTokenRequestDTO;
import com.example.chatservice.dto.ValidTokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "http://localhost:9002/auths")
public interface AuthFeignClient {

    @PostMapping("/validToken")
    ResponseEntity<ValidTokenResponseDTO> validToken(@RequestBody ValidTokenRequestDTO validTokenRequestDTO);
}
