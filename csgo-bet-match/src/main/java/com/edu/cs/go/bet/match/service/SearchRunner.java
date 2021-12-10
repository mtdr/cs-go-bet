package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.api.dto.common.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchRunner {

    private final List<Player> players;

    @Async
    public void searchRun() {
        while (!CollectionUtils.isEmpty(players)) {
            log.info("search for = {}", players);
        }
        // 
    }
}
