package com.pixelo.health.wellplate.myhealth.infrastructure.postgresql.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DietPostgresqlAdapter.class)
class DietPostgresqlAdapterTest {

    @Autowired
    private DietPostgresqlAdapter dietPostgresqlAdapter;

    @Test
    @DisplayName("식단 등록")
    void saveDiet() {
        var diet = Diet.builder()
                .healthId(UUID.randomUUID())
                .wellnessChallengerId(UUID.randomUUID())
                .mealTime(LocalDateTime.now())
                .build();
        Food food = Food.builder()
                .foodCode("1")
                .name("김치찌개")
                .calorie(BigDecimal.valueOf(199))
                .build();
        diet.updateFoodInfo(Collections.singletonList(food));

        Diet saved = dietPostgresqlAdapter.save(diet);
        Assertions.assertEquals(saved.dietId(), diet.dietId());
        Assertions.assertEquals(saved.foods().size(), diet.foods().size());
    }
}