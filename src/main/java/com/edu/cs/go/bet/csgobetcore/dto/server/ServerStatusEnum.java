package com.edu.cs.go.bet.csgobetcore.dto.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServerStatusEnum {

    SERVER_START("start"),
    SERVER_STOP("stop");

    private final String value;

}
