package com.edu.cs.go.bet.server.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "API Error enum code")
public enum ApiResponseErrorCode {
    NOT_FOUND,
    VALIDATION_FAILED,
    TIMEOUT,

}
