package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.dathost.client.ApiException;
import com.edu.cs.go.bet.server.dto.server.ServerDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusResponseDto;

public interface ServerService {
    ServerDto create() throws ApiException;

    ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto) throws ApiException;

    ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto) throws ApiException;
}
