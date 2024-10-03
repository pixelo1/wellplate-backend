package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.health;


import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(HealthPostgresqlAdapter.class)
class HealthPostgresqlAdapterTest {

    @Autowired
    private HealthPostgresqlAdapter healthPostgresqlAdapter;

    @Test
    @DisplayName("이용자의 건강 정보를 저장한다")
    void saveHealth() {
        Health health = Health.builder()
                .wellnessChallengerId(UUID.randomUUID())
                .baseBodyWeight(BigDecimal.valueOf(76))
                .goalBodyWeight(BigDecimal.valueOf(67))
                .build();

        Health savedHealth = healthPostgresqlAdapter.save(health);

        Assertions.assertThat(savedHealth.healthId()).isEqualTo(health.healthId());
        Assertions.assertThat(savedHealth.wellnessChallengerId()).isEqualTo(health.wellnessChallengerId());
        Assertions.assertThat(savedHealth.baseBodyWeight()).isEqualTo(health.baseBodyWeight());
        Assertions.assertThat(savedHealth.baseMeasurementUnit()).isEqualTo(health.baseMeasurementUnit());
        Assertions.assertThat(savedHealth.goalBodyWeight()).isEqualTo(health.goalBodyWeight());
        Assertions.assertThat(savedHealth.goalMeasurementUnit()).isEqualTo(health.goalMeasurementUnit());
    }
}