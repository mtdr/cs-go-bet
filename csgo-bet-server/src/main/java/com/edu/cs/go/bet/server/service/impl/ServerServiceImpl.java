package com.edu.cs.go.bet.server.service.impl;

import com.edu.cs.go.bet.server.configuration.AuthDatHostConfigurationProperties;
import com.edu.cs.go.bet.server.configuration.DatHostConfigurationProperties;
import com.edu.cs.go.bet.server.dto.server.ServerDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusEnum;
import com.edu.cs.go.bet.server.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.server.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
public class ServerServiceImpl implements ServerService {

    private static final AtomicLong SEQ = new AtomicLong(System.currentTimeMillis());

    private final RestTemplate restTemplate;
    private final DatHostConfigurationProperties datHostConfigurationProperties;
    private final AuthDatHostConfigurationProperties authDatHostConfigurationProperties;

    @Override
    public ServerDto create() {
        var createServerUrl = datHostConfigurationProperties.getHost() + datHostConfigurationProperties.getServers();

        var headers = getAuthFormHeaders();

        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("game", "csgo");
        map.add("name", String.valueOf(SEQ.incrementAndGet()));
        map.add("csgo_settings.rcon", authDatHostConfigurationProperties.getRcon());
        var request = new HttpEntity<>(map, headers);
        var response = restTemplate.postForEntity(createServerUrl, request, ServerDto.class);
        return response.getBody();
    }

    @Override
    public ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto) {
        return changeServerStatus(serverStatusRequestDto.getId(), ServerStatusEnum.SERVER_START);
    }

    @Override
    public ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto) {
        return changeServerStatus(serverStatusRequestDto.getId(), ServerStatusEnum.SERVER_STOP);
    }

    private HttpHeaders getAuthFormHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(authDatHostConfigurationProperties.getUsername(), authDatHostConfigurationProperties.getPassword());
        return headers;
    }

    private ServerStatusResponseDto changeServerStatus(String id, ServerStatusEnum status) {
        var startServerUrl = datHostConfigurationProperties.getHost() + datHostConfigurationProperties.getServers() + "/" + id + "/" + status.getValue();

        var headers = getAuthFormHeaders();

        var request = new HttpEntity<>(headers);
        var response = restTemplate.postForEntity(startServerUrl, request, ServerStatusResponseDto.class);
        var res = ServerStatusResponseDto.builder().id(id).build();
        if (response.getStatusCode().is2xxSuccessful()) {
            res.setStatus(status);
        } else {
            res.setStatus(Arrays.stream(ServerStatusEnum.values()).filter(s -> !s.equals(status)).findFirst().orElse(null));
        }
        return response.getBody();
    }
}
