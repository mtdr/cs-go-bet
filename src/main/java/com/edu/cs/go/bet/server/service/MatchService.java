package com.edu.cs.go.bet.server.service;

import com.edu.cs.go.bet.server.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.server.dto.match.CreateMatchResponseDto;

public interface MatchService {

    CreateMatchResponseDto create(CreateMatchRequestDto requestDto);
}
