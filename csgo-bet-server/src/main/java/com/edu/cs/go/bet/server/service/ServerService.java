package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.server.dto.server.ServerDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusResponseDto;

public interface ServerService {
    ServerDto create();

    ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto);

    ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto);
}
