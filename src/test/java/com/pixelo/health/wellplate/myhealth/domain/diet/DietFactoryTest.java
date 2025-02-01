package com.pixelo.health.wellplate.myhealth.domain.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.DietFactory;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DietFactoryTest {


    @Test
    void 식단_등록_Factory() {
        CreateDietDto.CreateFoodInfoDto foodInfoDto1 = CreateDietDto.CreateFoodInfoDto.builder()
                .foodCode("1")
                .foodName("된장찌개")
                .calorie(BigDecimal.valueOf(100))
                .build();
        CreateDietDto.CreateFoodInfoDto foodInfoDto2 = CreateDietDto.CreateFoodInfoDto.builder()
                .foodCode("2")
                .foodName("돼지고기")
                .calorie(BigDecimal.valueOf(300))
                .build();
        ArrayList<CreateDietDto.CreateFoodInfoDto> foodInfo = new ArrayList<>();
        foodInfo.add(foodInfoDto1);
        foodInfo.add(foodInfoDto2);
        CreateDietDto createDietDto = CreateDietDto.builder()
                .wellnessChallengerId(UUID.randomUUID())
                .healthId(UUID.randomUUID())
                .mealTime(LocalDateTime.now())
                .createFoodInfoDtos(foodInfo)
                .build();

        DietFactory dietFactory = new DietFactory();

        //when
        Diet diet = dietFactory.createDiet(createDietDto);

        //then
        assertThat(diet).isNotNull();
        assertThat(diet.dietId()).isNotNull();
        assertThat(diet.wellnessChallengerId()).isEqualTo(createDietDto.wellnessChallengerId());
        assertThat(diet.healthId()).isEqualTo(createDietDto.healthId());
        assertThat(diet.mealTime()).isEqualTo(createDietDto.mealTime());

        List<Food> foods = diet.foods();
        assertThat(foods.size()).isEqualTo(2);
        foods.sort(Comparator.comparing(Food::calorie).reversed());

        assertThat(foods.get(0).name()).isEqualTo("돼지고기");
        assertThat(foods.get(0).calorie()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(foods.get(1).name()).isEqualTo("된장찌개");
        assertThat(foods.get(1).calorie()).isEqualTo(BigDecimal.valueOf(100));
    }
}
