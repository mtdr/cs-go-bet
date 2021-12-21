package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.api.dto.common.GameDto;

public interface KafkaProducerService {

    void sendGame(GameDto gameDto);
}
