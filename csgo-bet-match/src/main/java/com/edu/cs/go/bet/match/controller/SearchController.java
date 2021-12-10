package com.edu.cs.go.bet.match.controller;

import com.edu.cs.go.bet.match.dto.MatchRegisterRequestDto;
import com.edu.cs.go.bet.match.dto.MatchRegisterResultDto;

public interface SearchController {

    MatchRegisterResultDto registerForSearch(MatchRegisterRequestDto user);
}
