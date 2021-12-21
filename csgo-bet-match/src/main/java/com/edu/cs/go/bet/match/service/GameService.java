package com.edu.cs.go.bet.match.service;

import com.edu.cs.go.bet.api.dto.common.GameDto;
import com.edu.cs.go.bet.api.dto.common.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameService {
    Optional<GameDto> getGame(UUID uuid);

    List<Player> getPlayersByGame(UUID uuid);

    Optional<GameDto> createGame(GameDto game);
}
