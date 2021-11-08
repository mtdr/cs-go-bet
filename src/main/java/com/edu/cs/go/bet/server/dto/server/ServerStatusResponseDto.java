package com.edu.cs.go.bet.server.dto.server;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerStatusResponseDto {
    private String id;

    private ServerStatusEnum status;
}
