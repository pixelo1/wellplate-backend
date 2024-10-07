package com.pixelo.health.wellplate.myhealth.application.in.command.health;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record RegisterHealthCommand(
        UUID wellnessChallengerId,
        BigDecimal baseBodyWeight,
        BigDecimal goalBodyWeight
) {
}
