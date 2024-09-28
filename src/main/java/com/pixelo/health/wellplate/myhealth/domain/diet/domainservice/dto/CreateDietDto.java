package com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateDietDto(
        UUID wellnessChallengerId,
        UUID healthId,
        LocalDate mealTime,
        List<CreateFoodInfoDto> createFoodInfoDtos
) {
    @Builder
    public record CreateFoodInfoDto(
            String foodName,
            BigDecimal calorie
    ) {
    }
}
