package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.server.dto.WebSocketMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.concurrent.atomic.AtomicLong;

public class WebSocketServiceImpl implements WebSocketService {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());

    @Override
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public WebSocketMessageDto send(String msg) {
        return WebSocketMessageDto.builder().msg(String.valueOf(SEQ.incrementAndGet())).build();
    }
}
