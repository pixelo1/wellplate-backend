package com.pixelo.health.wellplate.myhealth.domain.health.provider;

import java.math.BigDecimal;
import java.util.UUID;

public interface HealthProvider {

    UUID getHealthId();
    UUID getWellnessChallengerId();
    BigDecimal getBaseBodyWeight();
    String getBaseMeasurementUnit();
    BigDecimal getGoalBodyWeight();
    String getGoalMeasurementUnit();
}
