package com.edu.cs.go.bet.server.controller;

import com.edu.cs.go.bet.api.dto.common.ApiResponseDto;
import com.edu.cs.go.bet.api.dto.server.ServerDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.dathost.client.ApiException;

public interface ServerController {
    String BASE_PATH = "/api/server";
    String SERVER_CREATE = BASE_PATH + "/create";
    String SERVER_START = BASE_PATH + "/start";
    String SERVER_STOP = BASE_PATH + "/stop";

    ApiResponseDto<ServerDto> serverCreate() throws ApiException;

    ApiResponseDto<ServerStatusResponseDto> serverStart(ServerStatusRequestDto serverStatusRequestDto) throws ApiException;

    ApiResponseDto<ServerStatusResponseDto> serverStop(ServerStatusRequestDto serverStatusRequestDto) throws ApiException;
}
