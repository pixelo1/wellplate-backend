package com.pixelo.health.wellplate.myhealth.applidation.vo.diet;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FoodVo(
        String name,
        BigDecimal calorie
) {
}
