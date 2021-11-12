package com.edu.cs.go.bet.server.controller;

import com.edu.cs.go.bet.server.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.server.dto.match.CreateMatchResponseDto;

public interface MatchController {
    String BASE_PATH = "/api/match";
    String MATCH_CREATE = BASE_PATH + "/create";

    CreateMatchResponseDto matchCreate(CreateMatchRequestDto request);
}
