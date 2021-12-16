package com.edu.cs.go.bet.ws.configuration.websocket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketConfig {

    @Bean
    @ConfigurationProperties("websocket")
    WebSocketConfigProperties webSocketConfigProperties() {
        return new WebSocketConfigProperties();
    }

}
