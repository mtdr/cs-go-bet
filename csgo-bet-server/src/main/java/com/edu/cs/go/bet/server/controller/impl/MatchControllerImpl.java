package com.edu.cs.go.bet.server.controller.impl;

import com.edu.cs.go.bet.server.controller.MatchController;
import com.edu.cs.go.bet.server.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.server.dto.match.CreateMatchResponseDto;
import com.edu.cs.go.bet.server.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MatchControllerImpl implements MatchController {

    private final MatchService service;

    @Override
    @PostMapping(MATCH_CREATE)
    public CreateMatchResponseDto matchCreate(@RequestBody CreateMatchRequestDto request) {
        return service.create(request);
    }
}
