package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.server.dto.WebSocketMessageDto;

public interface WebSocketService {
    WebSocketMessageDto send(String msg);
}
