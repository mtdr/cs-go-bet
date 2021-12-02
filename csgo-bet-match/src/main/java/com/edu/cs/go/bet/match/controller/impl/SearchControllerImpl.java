package com.edu.cs.go.bet.match.controller.impl;

import com.edu.cs.go.bet.match.controller.SearchController;
import com.edu.cs.go.bet.match.dto.MatchStartDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchControllerImpl implements SearchController {

    @PostMapping(path = "/match/search/start")
    public MatchStartDto startSearch(UserDetails userDetails) {
        return MatchStartDto.builder().build();
    }
}
