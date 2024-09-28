package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.diet;

import com.pixelo.health.wellplate.myhealth.applidation.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
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
}
