package com.pixelo.health.wellplate.myhealth.applidation.in.command;

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
