package com.edu.cs.go.bet.api.dto.match;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * usernames -> steamIdList
 */
@Data
@AllArgsConstructor
public class CreateMatchRequestDto {

    private String serverId;

    private String mapId;

    private List<String> usernamesTeamA;

    private List<String> usernamesTeamB;
}
