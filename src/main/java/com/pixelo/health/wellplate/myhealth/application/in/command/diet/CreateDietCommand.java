package com.pixelo.health.wellplate.myhealth.application.in.command.diet;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateDietCommand(
        UUID wellnessChallengerId,
        UUID healthId,
        LocalDateTime mealTime,
        List<CreateFoodInfo> foodInfos
) {
    @Builder
    public record CreateFoodInfo(
            String foodName,
            BigDecimal calorie
    ) {
    }
}
