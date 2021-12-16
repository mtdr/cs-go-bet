package com.edu.cs.go.bet.server.kafka.listener;

import com.edu.cs.go.bet.api.dto.common.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameCreationListener {

    @KafkaListener(topics = "${kafka.consumer.topics:game}", groupId = "${kafka.consumer.groupId:default}")
    public void listenGameCreation(Game game) {
        log.info("[Server] Received Game: [{}]", game);
    }

}
