package com.edu.cs.go.bet.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Game {
    private List<Player> teamA;
    private List<Player> teamB;
    private GameStatusEnum status;
}
