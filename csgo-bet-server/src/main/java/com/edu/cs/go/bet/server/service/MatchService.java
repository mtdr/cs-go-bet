package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.api.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.api.dto.match.CreateMatchResponseDto;
import com.edu.cs.go.bet.dathost.client.ApiException;

public interface MatchService {

    CreateMatchResponseDto create(CreateMatchRequestDto requestDto) throws ApiException;
}
