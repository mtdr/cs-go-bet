package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.api.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.api.dto.match.CreateMatchResponseDto;

public interface MatchService {

    CreateMatchResponseDto create(CreateMatchRequestDto requestDto);
}
