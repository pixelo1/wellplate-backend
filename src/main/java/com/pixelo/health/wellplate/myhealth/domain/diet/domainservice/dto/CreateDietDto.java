package com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateDietDto(
        UUID wellnessChallengerId,
        UUID healthId,
        LocalDateTime mealTime,
        List<CreateFoodInfoDto> createFoodInfoDtos
) {
    @Builder
    public record CreateFoodInfoDto(
            String foodCode,
            String foodName,
            BigDecimal calorie
    ) {
    }
}
