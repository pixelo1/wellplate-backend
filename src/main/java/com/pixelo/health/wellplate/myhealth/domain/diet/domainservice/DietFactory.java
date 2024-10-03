package com.pixelo.health.wellplate.myhealth.domain.diet.domainservice;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class DietFactory {

    public static Diet createDiet(@NotNull CreateDietDto createDietDto) {
        var diet = Diet.builder()
                .healthId(createDietDto.healthId())
                .wellnessChallengerId(createDietDto.wellnessChallengerId())
                .mealTime(createDietDto.mealTime())
                .build();
        List<Food> foods = createFoods(createDietDto.createFoodInfoDtos());
        diet.updateFoodInfo(foods);
        return diet;
    }

    private static List<Food> createFoods(@NotEmpty List<CreateDietDto.CreateFoodInfoDto> dtos) {
        return dtos.stream()
                .map(DietFactory::createFood)
                .collect(Collectors.toList());
    }

    private static Food createFood(CreateDietDto.CreateFoodInfoDto dto) {
        return Food.builder()
                .name(dto.foodName())
                .calorie(dto.calorie())
                .build();
    }
}
