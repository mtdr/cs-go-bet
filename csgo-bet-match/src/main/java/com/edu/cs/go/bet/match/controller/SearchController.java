package com.edu.cs.go.bet.match.controller;

import com.edu.cs.go.bet.match.dto.MatchStartDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface SearchController {

    MatchStartDto startSearch(UserDetails userDetails);
}
