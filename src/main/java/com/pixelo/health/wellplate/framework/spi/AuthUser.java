package com.pixelo.health.wellplate.framework.spi;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AuthUser(
        UUID wellnessChallengerId
) {
}
