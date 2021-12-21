package com.edu.cs.go.bet.match.service.runner.impl;

import com.edu.cs.go.bet.api.dto.common.GameDto;
import com.edu.cs.go.bet.api.dto.common.GameStatusEnum;
import com.edu.cs.go.bet.api.dto.common.Player;
import com.edu.cs.go.bet.match.service.runner.SearchRunner;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class SimpleSearchRunnerImpl implements SearchRunner {

    private final List<Player> players;

    @SneakyThrows
    @Async
    public CompletableFuture<GameDto> searchRun() {
        log.info("{} search for = {}", Thread.currentThread(), players);
        // if match found - publish to kafka & save to db (maybe by kafka)
        //
        var teamA = List.of(Objects.requireNonNull(players.stream().findAny().orElse(null)));
        var teamB = List.of(Objects.requireNonNull(players.stream().filter(p -> !teamA.contains(p)).findAny().orElse(null)));
        return CompletableFuture.completedFuture(GameDto.builder()
                .teamA(teamA)
                .teamB(teamB)
                .status(GameStatusEnum.CREATED)
                .build());
    }
}
