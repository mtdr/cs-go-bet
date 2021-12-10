package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.api.dto.server.ServerDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.dathost.client.ApiException;

public interface ServerService {
    ServerDto create() throws ApiException;

    ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto) throws ApiException;

    ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto) throws ApiException;
}
