package com.edu.cs.go.bet.api.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Error description")
@Builder
public class ApiResponseError {

    @Schema(description = "Error enum code")
    private ApiResponseErrorCode code;

    @Schema(description = "Error message", maxLength = 1024)
    private String message;
}
