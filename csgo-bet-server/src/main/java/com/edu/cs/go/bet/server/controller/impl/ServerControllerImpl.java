package com.edu.cs.go.bet.server.controller.impl;

import com.edu.cs.go.bet.api.dto.common.ApiResponseDto;
import com.edu.cs.go.bet.api.dto.server.ServerDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.server.controller.ServerController;
import com.edu.cs.go.bet.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ServerControllerImpl implements ServerController {

    private final ServerService serverService;

    @Override
    @PostMapping(SERVER_CREATE)
    public ApiResponseDto<ServerDto> serverCreate() {
        return ApiResponseDto.success(serverService.create()).build();
    }

    @Override
    @PostMapping(SERVER_START)
    public ApiResponseDto<ServerStatusResponseDto> serverStart(@RequestBody ServerStatusRequestDto serverStatusRequestDto) {
        return ApiResponseDto.success(serverService.start(serverStatusRequestDto)).build();
    }

    @Override
    @PostMapping(SERVER_STOP)
    public ApiResponseDto<ServerStatusResponseDto> serverStop(@RequestBody ServerStatusRequestDto serverStatusRequestDto) {
        return ApiResponseDto.success(serverService.stop(serverStatusRequestDto)).build();
    }
}
