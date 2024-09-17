package com.pixelo.health.wellplate.authentication.application.in.command;

import lombok.Builder;

@Builder
public record RefreshTokenCommand(
        String refreshToken
) {
}
