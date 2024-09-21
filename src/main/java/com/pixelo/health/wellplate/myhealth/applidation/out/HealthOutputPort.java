package com.pixelo.health.wellplate.myhealth.applidation.out;

import com.pixelo.health.wellplate.myhealth.domain.health.Health;

public interface HealthOutputPort {

    Health save(Health health);
}
