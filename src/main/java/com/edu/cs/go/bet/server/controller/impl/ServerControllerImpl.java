package com.edu.cs.go.bet.server.controller.impl;

import com.edu.cs.go.bet.server.controller.ServerController;
import com.edu.cs.go.bet.server.dto.server.ServerDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusResponseDto;
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
    public ServerDto serverCreate() {
        return serverService.create();
    }

    @Override
    @PostMapping(SERVER_START)
    public ServerStatusResponseDto serverStart(@RequestBody ServerStatusRequestDto serverStatusRequestDto) {
        return serverService.start(serverStatusRequestDto);
    }

    @Override
    @PostMapping(SERVER_STOP)
    public ServerStatusResponseDto serverStop(@RequestBody ServerStatusRequestDto serverStatusRequestDto) {
        return serverService.stop(serverStatusRequestDto);
    }
}
