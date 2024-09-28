package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.diet;

import com.pixelo.health.wellplate.myhealth.applidation.out.DietOutputPort;
import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.Food;
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
    public void test() {
        Diet diet = Diet.builder()
                .healthId(UUID.randomUUID())
                .wellnessChallengerId(UUID.randomUUID())
                .mealTime(LocalDate.now())
                .build();
        Food food = Food.builder()
                .calorie(BigDecimal.valueOf(100))
                .name("100이다")
                .build();
        diet.updateFoodInfo(List.of(food));

        Diet save = dietPostgresqlRepository.save(diet);
        Diet diet2 = Diet.builder()
                .healthId(UUID.randomUUID())
                .wellnessChallengerId(UUID.randomUUID())
                .mealTime(LocalDate.now())
                .build();

        Diet saved2 = dietPostgresqlRepository.save(diet2);
    }

}
