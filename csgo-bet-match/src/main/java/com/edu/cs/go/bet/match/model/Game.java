package com.edu.cs.go.bet.match.model;

import com.edu.cs.go.bet.api.dto.common.GameStatusEnum;
import com.edu.cs.go.bet.api.dto.common.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    private UUID uuid;

    private GameStatusEnum status;

    private List<Player> aPlayers;

    private List<Player> bPlayers;
}
