package com.example.chatservice.service;

import com.example.chatservice.dto.AlarmMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {
    private final SimpMessagingTemplate messagingTemplate;

    public void alarmToUser(AlarmMessageDTO alarmMessageDTO) {
        String destination = "user".equals(alarmMessageDTO.getReceiverType())
                ? "/topic/alarm/user/" + alarmMessageDTO.getReceiverUid()
                : "/topic/alarm/social/" + alarmMessageDTO.getReceiverUid();

        log.info("알람 전송 대상 UID: {}", alarmMessageDTO.getReceiverUid());
        log.info("알람 전송 경로: {}", destination);

        messagingTemplate.convertAndSend(destination, alarmMessageDTO);

        log.info("메시지 전송 완료 -> user: {}, destination: {}, message: {}",
                alarmMessageDTO.getReceiverUid(), destination, alarmMessageDTO);
    }
}
