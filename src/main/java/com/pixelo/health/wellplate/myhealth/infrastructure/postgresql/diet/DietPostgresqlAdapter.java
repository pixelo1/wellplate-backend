package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.diet;

import com.pixelo.health.wellplate.myhealth.application.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class DietPostgresqlAdapter implements DietOutputPort {

    private final DietPostgresqlRepository dietPostgresqlRepository;

    @Override
    public Diet save(Diet diet) {
        return dietPostgresqlRepository.save(diet);
    }

    @Override
    public List<Diet> findByWellnessChallenger(UUID wellnessChallenger, UUID healthId) {
        return dietPostgresqlRepository.findByWellnessChallengerIdAndHealthId(wellnessChallenger, healthId);
    }
}
