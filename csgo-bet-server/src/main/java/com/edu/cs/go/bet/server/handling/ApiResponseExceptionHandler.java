package com.edu.cs.go.bet.server.handling;

import com.edu.cs.go.bet.dathost.client.ApiException;
import com.edu.cs.go.bet.server.dto.common.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.edu.cs.go.bet.server.controller")
public class ApiResponseExceptionHandler {

    @ExceptionHandler(InvalidPropertyException.class)
    public ResponseEntity<ApiResponseDto<?>> handleInvalidProperty(InvalidPropertyException ex) {
        log.error("An exception handled: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ApiResponseDto.badRequestError(ex.getMessage()).build());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponseDto<?>> handleApi(ApiException ex) {
        log.error("An exception handled: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(ApiResponseDto.badRequestError(ex.getMessage()).build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> handleNotFound(NotFoundException ex) {
        log.error("An exception handled: {}", ex.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<?>> handleInternal(Exception ex) {
        log.error("An exception handled: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body(ApiResponseDto.internalError(ex.getMessage()).build());
    }
}
