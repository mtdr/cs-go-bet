package com.edu.cs.go.bet.csgobetcore.controller.impl;

import com.edu.cs.go.bet.csgobetcore.controller.ServerController;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerDto;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.csgobetcore.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ServerStatusResponseDto serverStart(ServerStatusRequestDto serverStatusRequestDto) {
        return serverService.start(serverStatusRequestDto);
    }

    @Override
    @PostMapping(SERVER_STOP)
    public ServerStatusResponseDto serverStop(ServerStatusRequestDto serverStatusRequestDto) {
        return serverService.stop(serverStatusRequestDto);
    }
}
