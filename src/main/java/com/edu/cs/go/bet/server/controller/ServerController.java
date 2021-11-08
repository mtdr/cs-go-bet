package com.edu.cs.go.bet.server.controller;

import com.edu.cs.go.bet.server.dto.server.ServerDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusResponseDto;

public interface ServerController {
    String BASE_PATH = "/api/server";
    String SERVER_CREATE = BASE_PATH + "/create";
    String SERVER_START = BASE_PATH + "/start";
    String SERVER_STOP = BASE_PATH + "/stop";

    ServerDto serverCreate();

    ServerStatusResponseDto serverStart(ServerStatusRequestDto serverStatusRequestDto);

    ServerStatusResponseDto serverStop(ServerStatusRequestDto serverStatusRequestDto);
}
