package com.edu.cs.go.bet.api.dto.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServerStatusResponseDto {
    private String id;

    private ServerStatusEnum status;
}
