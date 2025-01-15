package com.pixelo.health.wellplate.myhealth.application.out;

import com.pixelo.health.wellplate.myhealth.domain.health.Health;

import java.util.Optional;
import java.util.UUID;

public interface HealthOutputPort {

    Health findByWellnessChallengerId(UUID wellnessChallengerId);
    Health save(Health health);
    void checkHealthIdOrException(UUID healthId);
}
