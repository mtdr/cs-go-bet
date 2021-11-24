package com.edu.cs.go.bet.server.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchResponseDto {

    private String connectLink;

    private String id;

    private String gameServerId;

    private List<String> team1SteamIds;

    private List<String> team2SteamIds;
}
