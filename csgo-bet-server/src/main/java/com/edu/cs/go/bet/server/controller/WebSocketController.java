package com.edu.cs.go.bet.server.controller;

import com.edu.cs.go.bet.api.dto.ws.ChatMessage;
import org.springframework.messaging.handler.annotation.Payload;

public interface WebSocketController {
    void send(@Payload ChatMessage chatMessage);
}
