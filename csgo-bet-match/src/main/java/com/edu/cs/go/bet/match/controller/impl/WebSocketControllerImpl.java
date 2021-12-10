package com.edu.cs.go.bet.match.controller.impl;

import com.edu.cs.go.bet.api.dto.ws.ChatMessage;
import com.edu.cs.go.bet.api.dto.ws.ChatNotification;
import com.edu.cs.go.bet.match.controller.WebSocketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebSocketControllerImpl implements WebSocketController {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    @MessageMapping("/chat")
    public void send(@Payload ChatMessage chatMessage) {
        log.info(chatMessage.toString());
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                new ChatNotification(
                        chatMessage.getId(),
                        chatMessage.getSenderId(),
                        chatMessage.getSenderName()));
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        var message = (GenericMessage) event.getMessage();
        var simpDestination = (String) message.getHeaders().get("simpDestination");
        log.info(event.toString());

        if (simpDestination.startsWith("/topic/group/1")) {
            // do stuff
        }
    }
}
