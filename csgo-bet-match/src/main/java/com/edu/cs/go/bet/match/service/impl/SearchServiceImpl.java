package com.edu.cs.go.bet.match.service.impl;

import com.edu.cs.go.bet.api.dto.common.Player;
import com.edu.cs.go.bet.api.dto.common.PlayerStatusEnum;
import com.edu.cs.go.bet.match.configuration.MatchMakingConfiguration;
import com.edu.cs.go.bet.match.dto.MatchRegisterResultDto;
import com.edu.cs.go.bet.match.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final MatchMakingConfiguration configuration;

    @Override
    public MatchRegisterResultDto registerForSearch(String username) {
        var res = configuration.add(new Player(username, PlayerStatusEnum.IN_SEARCH));
        // give ticket, add to some searching process
        return MatchRegisterResultDto.builder().order(res).build();
    }
}
