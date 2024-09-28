package com.pixelo.health.wellplate.myhealth.applidation.in.command.diet;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record CreateDietCommand(
        UUID wellnessChallengerId,
        UUID healthId,
        LocalDate mealTime,
        List<CreateFoodInfo> foodInfos
) {
    @Builder
    public record CreateFoodInfo(
            String foodName,
            BigDecimal calorie
    ) {
    }
}
