package com.pixelo.health.wellplate.myhealth.application.vo.diet;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record DietVo(
        UUID dietId,
        UUID healthId,
        UUID wellnessChallengerId,
        LocalDate mealTime,
        List<FoodVo> foodVos
) {
}
