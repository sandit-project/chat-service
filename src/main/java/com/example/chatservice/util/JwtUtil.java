//package com.example.chatservice.util;
//
//import io.jsonwebtoken.*;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private final String secretKey = "your-secret-key"; // Auth 서비스와 동일한 키일 경우 주의
//
//    // 토큰 검증
//    public void validateToken(String token) throws JwtException {
//        if (token == null || token.isBlank()) {
//            throw new JwtException("토큰이 없습니다.");
//        }
//
//        Jwts.parser()
//                .setSigningKey(secretKey.getBytes())
//                .parseClaimsJws(token);  // 유효하지 않으면 예외 발생
//    }
//
//    // 토큰에서 클레임(정보) 추출
//    public Claims getClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(secretKey.getBytes())
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    // 토큰에서 username 추출
//    public String getUsername(String token) {
//        return getClaims(token).getSubject();
//    }
//
//    // 토큰 만료 시간 확인
//    public boolean isTokenExpired(String token) {
//        Date expiration = getClaims(token).getExpiration();
//        return expiration.before(new Date());
//    }
//}
