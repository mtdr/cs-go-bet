package com.edu.cs.go.bet.match.mapper;

import com.edu.cs.go.bet.api.dto.common.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "uuid", source = "uuid")
    Player toPlayer(UUID uuid);

    List<Player> toPlayerList(List<UUID> uuidList);
//
//    default List<UUID> fromPlayerList(List<Player> players) {
//        return CollectionUtils.isEmpty(players)
//                ? Collections.emptyList()
//                : players.stream().map(Player::getUuid).collect(Collectors.toList());
//    }

}
