package com.edu.cs.go.bet.server.controller.impl;

import com.edu.cs.go.bet.server.controller.WebSocketController;
import com.edu.cs.go.bet.server.dto.ws.ChatMessage;
import com.edu.cs.go.bet.server.dto.ws.ChatNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Controller
public class WebSocketControllerImpl implements WebSocketController {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    @MessageMapping("/chat")
    public void send(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        chatMessage.getId(),
                        chatMessage.getSenderId(),
                        chatMessage.getSenderName()));
    }
}
