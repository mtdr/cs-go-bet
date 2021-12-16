package com.edu.cs.go.bet.ws.listener;

import com.edu.cs.go.bet.api.dto.common.Game;
import com.edu.cs.go.bet.ws.service.GameProcessorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameWsListener {
    private final GameProcessorService processorService;

    @KafkaListener(topics = "${kafka.consumer.topics:game}", groupId = "${kafka.consumer.groupId:default}")
    public void listenGame(Game game) {
        log.info("[WS] Received Game: [{}]", game);
        processorService.processGame(game);
    }
}
