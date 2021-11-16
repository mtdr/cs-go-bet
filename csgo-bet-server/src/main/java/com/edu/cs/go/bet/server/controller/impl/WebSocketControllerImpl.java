package com.edu.cs.go.bet.server.controller.impl;

import com.edu.cs.go.bet.server.controller.WebSocketController;
import com.edu.cs.go.bet.server.dto.GreetingMessage;
import com.edu.cs.go.bet.server.dto.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WebSocketControllerImpl implements WebSocketController {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());

    @Override
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingMessage send(HelloMessage msg) {
        return GreetingMessage.builder().content(SEQ.incrementAndGet() + " hello, " + msg.getName()).build();
    }
}
