package com.edu.cs.go.bet.server.kafka.listener;

import com.edu.cs.go.bet.api.dto.common.GameDto;
import com.edu.cs.go.bet.api.dto.common.GameStatusEnum;
import com.edu.cs.go.bet.api.dto.common.Player;
import com.edu.cs.go.bet.api.dto.match.CreateMatchRequestDto;
import com.edu.cs.go.bet.server.service.KafkaProducerService;
import com.edu.cs.go.bet.server.service.MatchService;
import com.edu.cs.go.bet.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameCreationListener {
    private final ServerService serverService;

    private final MatchService matchService;

    private final KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "${kafka.consumer.topics:game}", groupId = "${kafka.consumer.groupId:default}")
    public void listenGameCreation(GameDto gameDto) {
        log.info("[Server] Received Game: [{}]", gameDto);
        var server = serverService.create();
        var match = matchService.create(new CreateMatchRequestDto(server.getId(), null,
                gameDto.getTeamA().stream().map(Player::getUsername).collect(Collectors.toList()),
                gameDto.getTeamB().stream().map(Player::getUsername).collect(Collectors.toList())));
        if (match == null || match.getGameServerId() == null) {
            return;
        }
        gameDto.setStatus(GameStatusEnum.STARTED);
        kafkaProducerService.sendGame(gameDto);
    }
}
