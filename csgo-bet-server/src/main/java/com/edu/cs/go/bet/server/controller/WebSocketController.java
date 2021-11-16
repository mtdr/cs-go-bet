package com.edu.cs.go.bet.server.controller;

import com.edu.cs.go.bet.server.dto.GreetingMessage;
import com.edu.cs.go.bet.server.dto.HelloMessage;

public interface WebSocketController {
    GreetingMessage send(HelloMessage msg);
}
