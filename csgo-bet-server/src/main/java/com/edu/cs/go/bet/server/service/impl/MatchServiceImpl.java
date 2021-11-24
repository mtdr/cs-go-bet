package com.edu.cs.go.bet.server.service.impl;

import com.edu.cs.go.bet.dathost.client.ApiException;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.dathost.client.model.Match;
import com.edu.cs.go.bet.server.configuration.AuthDatHostConfigurationProperties;
import com.edu.cs.go.bet.server.configuration.DatHostConfigurationProperties;
import com.edu.cs.go.bet.server.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.server.dto.match.CreateMatchResponseDto;
import com.edu.cs.go.bet.server.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {

    private final RestTemplate restTemplate;
    private final DatHostConfigurationProperties datHostConfigurationProperties;
    private final AuthDatHostConfigurationProperties authDatHostConfigurationProperties;
    private final DatHostApi datHostApi;

    @Override
    public CreateMatchResponseDto create(CreateMatchRequestDto requestDto) throws ApiException {
        Match result = null;
        var request = datHostApi.postMatches()
                .gameServerId(requestDto.getServerId());

        if (!CollectionUtils.isEmpty(requestDto.getUsernamesTeamA())) {
            request.team1SteamIds(String.join(",", requestDto.getUsernamesTeamA()));
        }
        if (!CollectionUtils.isEmpty(requestDto.getUsernamesTeamB())) {
            request.team2SteamIds(String.join(",", requestDto.getUsernamesTeamB()));
        }
        if (StringUtils.hasLength(requestDto.getMapId())) {
            request.map(requestDto.getMapId());
        }
        result = request.execute();

        if (result == null) {
            return CreateMatchResponseDto.builder().build();
        }

        return CreateMatchResponseDto.builder().connectLink(datHostConfigurationProperties.getConnectLink())
                .id(result.getId())
                .build();
    }

    private HttpHeaders getAuthFormHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(authDatHostConfigurationProperties.getUsername(), authDatHostConfigurationProperties.getPassword());
        return headers;
    }
}
