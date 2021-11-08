package com.edu.cs.go.bet.csgobetcore.service.impl;

import com.edu.cs.go.bet.csgobetcore.dto.server.ServerDto;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerStatusRequestDto;
import com.edu.cs.go.bet.csgobetcore.dto.server.ServerStatusResponseDto;
import com.edu.cs.go.bet.csgobetcore.service.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ServerServiceImpl implements ServerService {

    private final RestTemplate restTemplate;

    @Override
    public ServerDto create() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        var map = Map.of(
                "game", "csgo",
                "name", "payplay-custom",
                "csgo_settings.rcon", ""
        );
        var request = new HttpEntity<>(map, headers);
        var response = restTemplate.postForEntity("", request, ServerDto.class);
        return response.getBody();
    }

    @Override
    public ServerStatusResponseDto start(ServerStatusRequestDto serverStatusRequestDto) {
        return null;
    }

    @Override
    public ServerStatusResponseDto stop(ServerStatusRequestDto serverStatusRequestDto) {
        return null;
    }
}
