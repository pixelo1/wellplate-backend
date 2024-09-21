package com.pixelo.health.wellplate.myhealth.domain.health.domainservice;

import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import com.pixelo.health.wellplate.myhealth.domain.health.domainservice.dtos.CreateHealthDto;

public class HealthFactory {

    public static Health createHealth(CreateHealthDto dto) {
        return Health.builder()
                .wellnessChallengerId(dto.wellnessChallengerId())
                .baseBodyWeight(dto.baseBodyWeight())
                .goalBodyWeight(dto.goalBodyWeight())
                .build();
    }
}
