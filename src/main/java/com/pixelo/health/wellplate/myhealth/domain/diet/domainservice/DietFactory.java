package com.pixelo.health.wellplate.myhealth.domain.diet.domainservice;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;

import java.util.List;

public class DietFactory {

    public static Diet createDiet(CreateDietDto createDietDto) {
        var diet = Diet.builder()
                .healthId(createDietDto.healthId())
                .wellnessChallengerId(createDietDto.wellnessChallengerId())
                .mealTime(createDietDto.mealTime())
                .build();
        List<Food> foods = createFoods(createDietDto.createFoodInfoDtos());
        diet.updateFoodInfo(foods);
        return diet;
    }

    private static List<Food> createFoods(List<CreateDietDto.CreateFoodInfoDto> dtos) {
        return dtos.stream()
                .map(DietFactory::createFood)
                .toList();
    }

    private static Food createFood(CreateDietDto.CreateFoodInfoDto dto) {
        return Food.builder()
                .name(dto.foodName())
                .calorie(dto.calorie())
                .build();
    }
}
