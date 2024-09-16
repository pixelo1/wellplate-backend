package com.pixelo.health.wellplate.authentication.application.vo;

import lombok.Builder;

@Builder
public record TokenVo(
        String accessToken,
        String refreshToken
) {
}
