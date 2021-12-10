package com.edu.cs.go.bet.api.dto.ws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}