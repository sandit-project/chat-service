package com.example.chatservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://himedia-a.com",
                        "http://himedia-a.com",
                        "https://ws.himedia-a.com",
                        "http://ws.himedia-a.com"
                )
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
