package com.edu.cs.go.bet.server.dto.match;

import lombok.Data;

import java.util.List;

@Data
public class CreateMatchResponseDto {

    private String connectLink;

    private String id;

    private String gameServerId;

    private List<String> team1SteamIds;

    private List<String> team2SteamIds;
}
