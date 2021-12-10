package com.edu.cs.go.bet.api.dto.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServerDto {

    private String id;

    private String name;

    private String connectLink;
}