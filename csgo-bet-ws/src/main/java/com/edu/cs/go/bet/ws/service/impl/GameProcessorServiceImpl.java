package com.edu.cs.go.bet.ws.service.impl;

import com.edu.cs.go.bet.api.dto.common.GameDto;
import com.edu.cs.go.bet.ws.service.GameProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameProcessorServiceImpl implements GameProcessorService {

    private final SimpMessagingTemplate wsTemplate;

    @Override
    public void processGame(GameDto gameDto) {
        log.info("Processing game [{}]", gameDto);
        for (var p : gameDto.getAllPlayers()) {
            wsTemplate.convertAndSendToUser(p.getUsername(), "/queue/messages", gameDto);
        }
    }
}
