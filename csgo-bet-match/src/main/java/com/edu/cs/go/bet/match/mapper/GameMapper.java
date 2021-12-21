package com.edu.cs.go.bet.match.mapper;

import com.edu.cs.go.bet.api.dto.common.GameDto;
import com.edu.cs.go.bet.match.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface GameMapper {

    @Mapping(target = "teamA", source = "APlayers")
    @Mapping(target = "teamB", source = "BPlayers")
    GameDto toDto(Game game);

    @Mapping(target = "aPlayers", source = "teamA")
    @Mapping(target = "bPlayers", source = "teamB")
    Game toModel(GameDto gameDto);
}
