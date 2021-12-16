package com.edu.cs.go.bet.ws.configuration.websocket;

import lombok.Data;

@Data
public class WebSocketConfigProperties {

    private String destination;

    private String userPrefix;

    private String appPrefix;

    private String broker;

}
