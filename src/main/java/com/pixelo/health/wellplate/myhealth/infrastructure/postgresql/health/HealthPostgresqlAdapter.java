package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.health;

import com.pixelo.health.wellplate.myhealth.application.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HealthPostgresqlAdapter implements HealthOutputPort {

    private final HealthPostgresqlRepository healthPostgresqlRepository;

    @Override
    public Health findByWellnessChallengerId(UUID wellnessChallengerId) {
        return healthPostgresqlRepository.findByWellnessChallengerId(wellnessChallengerId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 건강 정보가 없습니다."));
    }

    @Override
    public Health save(Health health) {
        return healthPostgresqlRepository.save(health);
    }

    @Override
    public void checkHealthIdOrException(UUID healthId) {
        healthPostgresqlRepository.findById(healthId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 건강 정보가 없습니다."));
    }
}
