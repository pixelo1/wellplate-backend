package com.pixelo.health.wellplate.framework.spi;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "API 응답")
public record ResultResponse<T>(
        @Schema(description = "요청 결과 메시지", nullable = true, example = "null") String message,
        @Schema(description = "요청 결과") T data)
{
    public static <T> ResultResponse<T> ok(T data) {
        return new ResultResponse<>(null, data);
    }
    public static <T> ResultResponse<T> failedAbout(String message) {
        return new ResultResponse<>(message, null);
    }
}
