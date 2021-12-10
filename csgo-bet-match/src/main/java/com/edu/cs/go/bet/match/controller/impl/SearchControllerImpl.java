package com.edu.cs.go.bet.match.controller.impl;

import com.edu.cs.go.bet.match.controller.SearchController;
import com.edu.cs.go.bet.match.dto.MatchRegisterRequestDto;
import com.edu.cs.go.bet.match.dto.MatchRegisterResultDto;
import com.edu.cs.go.bet.match.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchControllerImpl implements SearchController {

    private final SearchService service;

    @PostMapping(path = "/api/match/register")
    public MatchRegisterResultDto registerForSearch(@RequestBody MatchRegisterRequestDto user) {
        return service.registerForSearch(user.getUsername());
    }
}
