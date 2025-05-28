package com.example.chatservice.controller;

import com.example.chatservice.dto.AlarmMessageDTO;
import com.example.chatservice.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @PostMapping
    public ResponseEntity<Void> alarmToUser(@RequestBody AlarmMessageDTO alarmMessageDTO) {
        log.info("Alarm Controller received alarm message: {}", alarmMessageDTO.getMerchantUid()+" "+alarmMessageDTO.getEventType()+" "+alarmMessageDTO.getMessage());
        alarmService.alarmToUser(alarmMessageDTO);
        return ResponseEntity.ok().build();
    }
}
