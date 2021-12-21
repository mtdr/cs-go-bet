package com.edu.cs.go.bet.match.service.impl;

import com.edu.cs.go.bet.api.dto.common.GameDto;
import com.edu.cs.go.bet.api.dto.common.Player;
import com.edu.cs.go.bet.match.mapper.GameMapper;
import com.edu.cs.go.bet.match.repository.GameRepository;
import com.edu.cs.go.bet.match.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    @Override
    public Optional<GameDto> getGame(UUID uuid) {
        if (uuid == null) {
            return Optional.empty();
        }
        return repository.findById(uuid).map(mapper::toDto);
    }

    @Override
    public List<Player> getPlayersByGame(UUID uuid) {
        return repository.findById(uuid).map(mapper::toDto).map(GameDto::getAllPlayers).orElse(Collections.emptyList());
    }

    @Override
    public Optional<GameDto> createGame(GameDto gameDto) {
        var gameToSave = mapper.toModel(gameDto);
        var savedGame = repository.save(gameToSave);
        return Optional.of(mapper.toDto(savedGame));
    }
}
