package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.health;

import com.pixelo.health.wellplate.myhealth.applidation.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HealthPostgresqlAdapter implements HealthOutputPort {

    private final HealthPostgresqlRepository healthPostgresqlRepository;

    @Override
    public Health save(Health health) {
        return healthPostgresqlRepository.save(health);
    }
}
