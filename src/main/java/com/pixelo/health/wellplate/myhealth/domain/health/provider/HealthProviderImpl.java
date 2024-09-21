package com.pixelo.health.wellplate.myhealth.domain.health.provider;

import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor(staticName = "from")
public class HealthProviderImpl implements HealthProvider{
    private final Health health;

    public UUID getHealthId() {
        return health.healthId();
    }
    public UUID getWellnessChallengerId() {return health.wellnessChallengerId();}

    public BigDecimal getBaseBodyWeight() {
        return health.baseBodyWeight();
    }

    public String getBaseMeasurementUnit() {
        return health.baseMeasurementUnit();
    }

    public BigDecimal getGoalBodyWeight() {
        return health.goalBodyWeight();
    }

    public String getGoalMeasurementUnit() {
        return health.goalMeasurementUnit();
    }
}
