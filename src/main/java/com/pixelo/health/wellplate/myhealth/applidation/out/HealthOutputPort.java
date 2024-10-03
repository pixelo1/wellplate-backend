package com.pixelo.health.wellplate.myhealth.applidation.out;

import com.pixelo.health.wellplate.myhealth.domain.health.Health;

import java.util.UUID;

public interface HealthOutputPort {

    Health save(Health health);
    void checkHealthIdOrException(UUID healthId);
}
