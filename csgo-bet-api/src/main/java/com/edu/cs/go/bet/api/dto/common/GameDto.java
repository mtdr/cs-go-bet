package com.edu.cs.go.bet.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameDto {
    private UUID uuid;
    private List<Player> teamA;
    private List<Player> teamB;
    private GameStatusEnum status;

    public List<Player> getAllPlayers() {
        return Stream.concat(teamA.stream(), teamB.stream()).collect(Collectors.toList());
    }
}
