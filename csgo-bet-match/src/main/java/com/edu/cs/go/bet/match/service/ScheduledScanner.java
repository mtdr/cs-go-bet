package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.match.configuration.MatchMakingConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledScanner {

    private final MatchMakingConfiguration matchMakingConfiguration;

    @Scheduled(fixedRate = 10000)
    public void scheduleFixedRateTaskAsync() {
        log.info("queue = {}", matchMakingConfiguration.getPlayers());
        var runner = new SearchRunner(matchMakingConfiguration.getPlayers().subList(0, 1));
        runner.searchRun();
        //
    }
}
