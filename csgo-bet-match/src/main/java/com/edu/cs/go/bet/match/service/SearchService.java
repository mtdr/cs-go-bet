package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.match.dto.MatchStartDto;

public interface SearchService {
    MatchStartDto registerForSearch(String username);
}
