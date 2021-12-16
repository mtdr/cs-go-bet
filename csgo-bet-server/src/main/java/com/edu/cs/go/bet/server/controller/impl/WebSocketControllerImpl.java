package com.edu.cs.go.bet.server.controller.impl;

import com.edu.cs.go.bet.api.dto.ws.ChatMessage;
import com.edu.cs.go.bet.api.dto.ws.ChatNotification;
import com.edu.cs.go.bet.server.controller.WebSocketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WebSocketControllerImpl implements WebSocketController {

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

    @GetMapping("/messages/{senderId}/{recipientId}")
    @ResponseBody
    public List<ChatMessage> findChatMessages(@PathVariable String senderId,
                                              @PathVariable String recipientId) {
        return List.of(ChatMessage.builder().senderId("1").recipientId("2").content("test").build());
    }

    @SubscribeMapping("/user/1/queue/messages")
    @ResponseBody
    public List<ChatMessage> chatInit() {
        return List.of(ChatMessage.builder().senderId("1").recipientId("2").content("test").build());
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        GenericMessage message = (GenericMessage) event.getMessage();
        String simpDestination = (String) message.getHeaders().get("simpDestination");
        log.info(event.toString());

        if (simpDestination.startsWith("/topic/group/1")) {
            // do stuff
        }
    }
}
