package com.edu.cs.go.bet.csgobetcore.service;

import com.edu.cs.go.bet.csgobetcore.dto.server.ServerDto;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerStatusResponseDto;

public interface ServerService {
    ServerDto create();

    ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto);

    ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto);
}
