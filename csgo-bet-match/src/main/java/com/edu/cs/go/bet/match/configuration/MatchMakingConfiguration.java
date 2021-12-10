package com.edu.cs.go.bet.match.configuration;

import com.edu.cs.go.bet.api.dto.common.Player;
import com.edu.cs.go.bet.api.dto.common.PlayerStatusEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Data
@Slf4j
@EnableAsync
@EnableScheduling
public class MatchMakingConfiguration {
    private final List<Player> players;

    public int add(Player player) {
        try {
            players.add(player);
            return players.size();
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            return -1;
        }
    }

    public List<Player> getAvailable() {
        return players.stream().filter(p -> p.getStatus().equals(PlayerStatusEnum.IN_SEARCH)).collect(Collectors.toList());
    }

    public boolean remove(List<Player> playersToRemove) {
        return players.removeAll(playersToRemove);
    }
}
