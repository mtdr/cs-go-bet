package com.edu.cs.go.bet.server.service.impl;

import com.edu.cs.go.bet.api.dto.server.ServerDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusEnum;
import com.edu.cs.go.bet.api.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.api.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.dathost.client.ApiException;
import com.edu.cs.go.bet.dathost.client.ApiResponse;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.dathost.client.model.GameServer;
import com.edu.cs.go.bet.server.configuration.AuthDatHostConfigurationProperties;
import com.edu.cs.go.bet.server.configuration.DatHostConfigurationProperties;
import com.edu.cs.go.bet.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
@Slf4j
public class ServerServiceImpl implements ServerService {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());

    private final DatHostConfigurationProperties datHostConfigurationProperties;
    private final AuthDatHostConfigurationProperties authDatHostConfigurationProperties;
    private final DatHostApi datHostApi;

    @Override
    public ServerDto create() {
        var name = String.valueOf(SEQ.incrementAndGet());
        DatHostApi.APIpostGameServersRequest request;
        try {
            request = datHostApi.postGameServers()
                    .game("csgo")
                    .name(name)
                    .csgoSettingsRcon(authDatHostConfigurationProperties.getRcon());
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        ApiResponse<GameServer> response = null;
        try {
            response = request.executeWithHttpInfo();
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
        }
        if (response != null && (response.getStatusCode() != 200 || response.getData() == null)) {
            try {
                throw new ApiException(response.getStatusCode(), "Error while creating server");
            } catch (ApiException e) {
                log.error(e.getMessage(), e);
            }
        }
        var connectLink = datHostConfigurationProperties.getConnectLink();
        return ServerDto.builder().id(response.getData().getId()).connectLink(connectLink).name(name).build();
    }

    @Override
    public ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto) {
        try {
            var res = datHostApi.postGameServerStart(serverStatusRequestDto.getId()).executeWithHttpInfo();
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return ServerStatusResponseDto.builder().status(ServerStatusEnum.SERVER_START).build();
    }

    @Override
    public ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto) {
        try {
            var res = datHostApi.postGameServerStop(serverStatusRequestDto.getId()).executeWithHttpInfo();
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return ServerStatusResponseDto.builder().status(ServerStatusEnum.SERVER_STOP).build();
    }

}
