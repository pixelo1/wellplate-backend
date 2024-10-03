package com.pixelo.health.wellplate.myhealth.domain.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.DietFactory;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class DietTest {

    @Test
    void 식단_등록_Factory() {
        CreateDietDto.CreateFoodInfoDto foodInfoDto1 = CreateDietDto.CreateFoodInfoDto.builder()
                .foodName("된장찌개")
                .calorie(BigDecimal.valueOf(100))
                .build();
        CreateDietDto.CreateFoodInfoDto foodInfoDto2 = CreateDietDto.CreateFoodInfoDto.builder()
                .foodName("돼지고기")
                .calorie(BigDecimal.valueOf(300))
                .build();
        ArrayList<CreateDietDto.CreateFoodInfoDto> foodInfo = new ArrayList<>();
        foodInfo.add(foodInfoDto1);
        foodInfo.add(foodInfoDto2);
        CreateDietDto createDietDto = CreateDietDto.builder()
                .wellnessChallengerId(UUID.randomUUID())
                .healthId(UUID.randomUUID())
                .mealTime(LocalDate.now())
                .createFoodInfoDtos(foodInfo)
                .build();
        //when
        Diet diet = DietFactory.createDiet(createDietDto);

        //then
        assertThat(diet).isNotNull();
        assertThat(diet.dietId()).isNotNull();
        assertThat(diet.wellnessChallengerId()).isEqualTo(createDietDto.wellnessChallengerId());
        assertThat(diet.healthId()).isEqualTo(createDietDto.healthId());
        assertThat(diet.mealTime().date()).isEqualTo(createDietDto.mealTime());

        List<Food> foods = diet.foodInfo().foods();
        assertThat(foods.size()).isEqualTo(2);
        foods.sort(Comparator.comparing(Food::calorie).reversed());

        assertThat(foods.get(0).name()).isEqualTo("돼지고기");
        assertThat(foods.get(0).calorie()).isEqualTo(BigDecimal.valueOf(300));
        assertThat(foods.get(1).name()).isEqualTo("된장찌개");
        assertThat(foods.get(1).calorie()).isEqualTo(BigDecimal.valueOf(100));
    }

    static Stream<Arguments> dietRequiredNullParam() {
        return Stream.of(
                Arguments.of(null, UUID.randomUUID(), LocalDate.now(), "건강 정보 ID는 필수 입니다."),
                Arguments.of(UUID.randomUUID(), null, LocalDate.now(), "이용자 ID는 필수 입니다."),
                Arguments.of(UUID.randomUUID(), UUID.randomUUID(), null, "섭취 시간은 는 필수 입니다.")
        );
    }

    @ParameterizedTest
    @MethodSource("dietRequiredNullParam")
    void Diet_필수값_null_테스트(UUID healthId, UUID wellnessChallengerId, LocalDate mealTime, String expectedMessage) {
        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Diet.builder()
                    .healthId(healthId)
                    .wellnessChallengerId(wellnessChallengerId)
                    .mealTime(mealTime)
                    .build();
        });
        //then
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    static Stream<Arguments> foodRequiredNullParam() {
        return Stream.of(
                Arguments.of(null, BigDecimal.valueOf(13), "음식명은 필수 입니다."),
                Arguments.of("", BigDecimal.valueOf(13), "음식명은 필수 입니다."),
                Arguments.of("sefg", null, "칼로리는 필수 입니다.")
        );
    }

    @ParameterizedTest
    @MethodSource("foodRequiredNullParam")
    void Food_필수값_null_테스트(String name, BigDecimal calorie, String expectedMessage) {
        //when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Food.builder()
                    .name(name)
                    .calorie(calorie)
                    .build();
        });
        //then
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }

    static Stream<Arguments> foodInfoRequiredNullParam() {
        return Stream.of(
                Arguments.of(null, "음식정보는 필수 입니다."),
                Arguments.of(Collections.EMPTY_LIST, "음식정보는 필수 입니다.")
        );
    }

    @ParameterizedTest
    @MethodSource("foodInfoRequiredNullParam")
    void FoodInfo_업데이트_필수값(List<Food> foods, String expectedMessage) {
        //when
        Diet diet = Diet.builder()
                .healthId(UUID.randomUUID())
                .wellnessChallengerId(UUID.randomUUID())
                .mealTime(LocalDate.now())
                .build();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            diet.updateFoodInfo(foods);
        });

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}