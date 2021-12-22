package com.edu.cs.go.bet.server.service.impl;

import com.edu.cs.go.bet.api.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.api.dto.match.CreateMatchResponseDto;
import com.edu.cs.go.bet.api.exception.NotFoundException;
import com.edu.cs.go.bet.dathost.client.ApiException;
import com.edu.cs.go.bet.dathost.client.api.DatHostApi;
import com.edu.cs.go.bet.dathost.client.model.Match;
import com.edu.cs.go.bet.server.configuration.DatHostConfigurationProperties;
import com.edu.cs.go.bet.server.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {

    private final DatHostConfigurationProperties datHostConfigurationProperties;
    private final DatHostApi datHostApi;

    @Override
    public CreateMatchResponseDto create(CreateMatchRequestDto requestDto) {
        Match result = null;
        DatHostApi.APIpostMatchesRequest request = null;
        try {
            request = datHostApi.postMatches()
                    .gameServerId(requestDto.getServerId());
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (!CollectionUtils.isEmpty(requestDto.getUsernamesTeamA())) {
            request.team1SteamIds(String.join(",", requestDto.getUsernamesTeamA()));
        }
        if (!CollectionUtils.isEmpty(requestDto.getUsernamesTeamB())) {
            request.team2SteamIds(String.join(",", requestDto.getUsernamesTeamB()));
        }
        if (StringUtils.hasLength(requestDto.getMapId())) {
            request.map(requestDto.getMapId());
        }
        try {
            result = request.execute();
        } catch (ApiException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (result == null) {
            throw new NotFoundException();
        }

        return CreateMatchResponseDto.builder().connectLink(datHostConfigurationProperties.getConnectLink())
                .id(result.getId())
                .build();
    }

}
