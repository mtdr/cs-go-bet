package com.edu.cs.go.bet.csgobetcore.dto.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServerStatusEnum {

    SERVER_STARTED("server_started"),
    SERVER_STOPPED("server_stopped");

    private final String value;

}
