package com.edu.cs.go.bet.server.dto.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ServerStatusEnum {

    SERVER_START("start"),
    SERVER_STOP("stop");

    private final String value;

    public static ServerStatusEnum getAnotherStatus(ServerStatusEnum status) {
        return Arrays.stream(ServerStatusEnum.values()).filter(s -> !s.equals(status)).findFirst().orElse(null);
    }

}
