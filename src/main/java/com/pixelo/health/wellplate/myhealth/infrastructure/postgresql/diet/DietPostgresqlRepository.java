package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DietPostgresqlRepository extends JpaRepository<Diet, UUID> {

    List<Diet> findByWellnessChallengerIdAndHealthId(UUID wellnessChallengerId, UUID healthId);
}
