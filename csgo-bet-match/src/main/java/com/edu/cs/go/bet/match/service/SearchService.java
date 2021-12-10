package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.match.dto.MatchRegisterResultDto;

public interface SearchService {
    MatchRegisterResultDto registerForSearch(String username);
}
