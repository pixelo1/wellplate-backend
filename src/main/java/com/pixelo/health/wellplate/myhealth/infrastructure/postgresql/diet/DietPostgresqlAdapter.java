package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.diet;

import com.pixelo.health.wellplate.myhealth.application.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DietPostgresqlAdapter implements DietOutputPort {

    private final DietPostgresqlRepository dietPostgresqlRepository;

    @Override
    public Diet save(Diet diet) {
        return dietPostgresqlRepository.save(diet);
    }
}
