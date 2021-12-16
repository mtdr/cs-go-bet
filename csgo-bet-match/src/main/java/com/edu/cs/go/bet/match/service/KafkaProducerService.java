package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.api.dto.common.Game;

public interface KafkaProducerService {

    void sendGame(Game game);
}
