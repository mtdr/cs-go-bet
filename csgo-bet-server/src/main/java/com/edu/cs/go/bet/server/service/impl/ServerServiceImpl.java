package com.edu.cs.go.bet.server.service.impl;

import com.edu.cs.go.bet.dathost.client.ApiException;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.server.configuration.AuthDatHostConfigurationProperties;
import com.edu.cs.go.bet.server.configuration.DatHostConfigurationProperties;
import com.edu.cs.go.bet.server.dto.server.ServerDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusEnum;
import com.edu.cs.go.bet.server.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
public class ServerServiceImpl implements ServerService {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());

    private final DatHostConfigurationProperties datHostConfigurationProperties;
    private final AuthDatHostConfigurationProperties authDatHostConfigurationProperties;
    private final DatHostApi datHostApi;

    @Override
    public ServerDto create() throws ApiException {
        var name = String.valueOf(SEQ.incrementAndGet());
        var request = datHostApi.postGameServers()
                .game("csgo")
                .name(name)
                .csgoSettingsRcon(authDatHostConfigurationProperties.getRcon());

        var response = request.executeWithHttpInfo();
        if (response.getStatusCode() != 200) {
            throw new ApiException(response.getStatusCode(), "Error while creating server");
        }
        var connectLink = datHostConfigurationProperties.getConnectLink();
        return ServerDto.builder().connectLink(connectLink).name(name).build();
    }

    @Override
    public ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto) throws ApiException {
        var res = datHostApi.postGameServerStart(serverStatusRequestDto.getId()).executeWithHttpInfo();
        return ServerStatusResponseDto.builder().status(ServerStatusEnum.SERVER_START).build();
    }

    @Override
    public ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto) throws ApiException {
        var res = datHostApi.postGameServerStop(serverStatusRequestDto.getId()).executeWithHttpInfo();
        return ServerStatusResponseDto.builder().status(ServerStatusEnum.SERVER_STOP).build();
    }

}
