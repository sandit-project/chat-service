package com.example.chatservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidTokenResponseDTO {
    private boolean valid;
    private String role;  // 사용자 역할 (예: "ROLE_USER", "ROLE_ADMIN")
}
