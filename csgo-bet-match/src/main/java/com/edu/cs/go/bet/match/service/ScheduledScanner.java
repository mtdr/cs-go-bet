package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.api.dto.common.PlayerStatusEnum;
import com.edu.cs.go.bet.match.configuration.MatchMakingConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduledScanner {

    private final MatchMakingConfiguration matchMakingConfiguration;

    @Scheduled(fixedRate = 10000)
    @Async
    public void scheduleFixedRateTaskAsync() {
        var players = matchMakingConfiguration.getAvailable();
        log.info("queue = {}", players);
        if (CollectionUtils.isEmpty(players) || players.size() < 2) {
            return;
        }
        players.forEach(p -> p.setStatus(PlayerStatusEnum.PROCESSING));
        var runner = new SearchRunner(players);
        runner.searchRun().whenCompleteAsync((g, t) -> {
            if (t != null) {
                log.error(t.getMessage(), t);
                return;
            }
            var playersInGame = Stream.concat(g.getTeamA().stream(), g.getTeamB().stream()).collect(Collectors.toList());
            playersInGame.forEach(p -> p.setStatus(PlayerStatusEnum.RESPONSING_GAME));
            matchMakingConfiguration.remove(playersInGame);
            log.info("Created game {}", g);
        });

        // send to ws
        // receive all ack
        // send match pair to kafka
        //
    }
}
