package com.edu.cs.go.bet.server.service.impl;

import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.server.configuration.AuthDatHostConfigurationProperties;
import com.edu.cs.go.bet.server.configuration.DatHostConfigurationProperties;
import com.edu.cs.go.bet.server.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.server.dto.match.CreateMatchResponseDto;
import com.edu.cs.go.bet.server.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final RestTemplate restTemplate;
    private final DatHostConfigurationProperties datHostConfigurationProperties;
    private final AuthDatHostConfigurationProperties authDatHostConfigurationProperties;
    private final DatHostApi datHostApi;

    @Override
    public CreateMatchResponseDto create(CreateMatchRequestDto requestDto) {
        var createServerUrl = datHostConfigurationProperties.getHost() + datHostConfigurationProperties.getMatches();

        var headers = getAuthFormHeaders();

        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("game_server_id", requestDto.getServerId());
        if (!CollectionUtils.isEmpty(requestDto.getUsernamesTeamA())) {
            map.add("team1_steam_ids", String.join(",", requestDto.getUsernamesTeamA()));
        }
        if (!CollectionUtils.isEmpty(requestDto.getUsernamesTeamB())) {
            map.add("team2_steam_ids", String.join(",", requestDto.getUsernamesTeamB()));
        }
        if (StringUtils.hasLength(requestDto.getMapId())) {
            map.add("map", requestDto.getMapId());
        }

//        var res = datHostApi.postMatches(null);

        var request = new HttpEntity<>(map, headers);
        var response = restTemplate.postForEntity(createServerUrl, request, CreateMatchResponseDto.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            response.getBody().setConnectLink(datHostConfigurationProperties.getConnectLink());
        }
        return response.getBody();
    }

    private HttpHeaders getAuthFormHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(authDatHostConfigurationProperties.getUsername(), authDatHostConfigurationProperties.getPassword());
        return headers;
    }

    private void test() {
    }
}
