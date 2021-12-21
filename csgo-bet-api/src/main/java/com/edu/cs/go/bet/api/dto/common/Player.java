package com.edu.cs.go.bet.api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Player {
    private UUID uuid;
    private String username;
    private PlayerStatusEnum status;
}
