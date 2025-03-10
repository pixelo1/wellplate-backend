package com.pixelo.health.wellplate.myhealth.application.vo.diet;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record DietVo(
        UUID dietId,
        UUID healthId,
        UUID wellnessChallengerId,
        LocalDateTime mealTime,
        BigDecimal totalCalories,
        List<FoodVo> foodVos
) {
}
