package com.pixelo.health.wellplate.myhealth.applidation.vo;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record HealthVo(
        UUID healthId,
        UUID wellnessChallengerId,
        BigDecimal baseBodyWeight,
        String baseMeasurementUnit,
        BigDecimal goalBodyWeight,
        String goalMeasurementUnit
) {
}
