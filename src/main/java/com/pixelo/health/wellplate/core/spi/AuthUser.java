package com.pixelo.health.wellplate.core.spi;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthUser(
        UUID wellnessChallengerId
) {
}
