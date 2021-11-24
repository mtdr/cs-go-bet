package com.edu.cs.go.bet.server.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiResponseDto<T> {

    @Schema(description = "HTTP status code")
    private HttpStatus status;

    @Schema(description = "Response body")
    private T payload;

    @Schema(description = "Errors")
    private List<ApiResponseError> errors;

    public static <T> ApiResponseDtoBuilder<T> success() {
        return ApiResponseDto.<T>builder().status(HttpStatus.OK);
    }

    public static <T> ApiResponseDtoBuilder<T> success(T payload) {
        return ApiResponseDto.<T>builder().status(HttpStatus.OK).payload(payload);
    }

    public static <T> ApiResponseDtoBuilder<T> badRequestError() {
        return ApiResponseDto.<T>builder().status(HttpStatus.BAD_REQUEST).errors(List.of(ApiResponseError.builder().code(ApiResponseErrorCode.VALIDATION_FAILED).build()));
    }

    public static <T> ApiResponseDtoBuilder<T> badRequestError(String msg) {
        return ApiResponseDto.<T>builder().status(HttpStatus.BAD_REQUEST).errors(List.of(ApiResponseError.builder().code(ApiResponseErrorCode.VALIDATION_FAILED).message(msg).build()));
    }

    public static <T> ApiResponseDtoBuilder<T> notFound() {
        return ApiResponseDto.<T>builder().status(HttpStatus.NOT_FOUND).errors(List.of(ApiResponseError.builder().code(ApiResponseErrorCode.NOT_FOUND).build()));
    }

    public static <T> ApiResponseDtoBuilder<T> notFound(String msg) {
        return ApiResponseDto.<T>builder().status(HttpStatus.NOT_FOUND).errors(List.of(ApiResponseError.builder().code(ApiResponseErrorCode.NOT_FOUND).message(msg).build()));
    }

    public static <T> ApiResponseDtoBuilder<T> internalError() {
        return ApiResponseDto.<T>builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(List.of(ApiResponseError.builder().code(ApiResponseErrorCode.INTERNAL).build()));
    }

    public static <T> ApiResponseDtoBuilder<T> internalError(String msg) {
        return ApiResponseDto.<T>builder().status(HttpStatus.INTERNAL_SERVER_ERROR).errors(List.of(ApiResponseError.builder().code(ApiResponseErrorCode.INTERNAL).message(msg).build()));
    }

}
