package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.health;

import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HealthPostgresqlRepository extends JpaRepository<Health, UUID> {

    Optional<Health> findByWellnessChallengerId(UUID wellnessChallengerId);
}
