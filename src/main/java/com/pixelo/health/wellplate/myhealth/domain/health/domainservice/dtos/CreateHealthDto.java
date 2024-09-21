package com.pixelo.health.wellplate.myhealth.domain.health.domainservice.dtos;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record CreateHealthDto(
        UUID wellnessChallengerId,
        BigDecimal baseBodyWeight,
        BigDecimal goalBodyWeight
) {
}
