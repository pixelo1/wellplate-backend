package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "accessToken 재발급 요청")
public class RefreshTokenRequest {
    private String refreshToken;
}
