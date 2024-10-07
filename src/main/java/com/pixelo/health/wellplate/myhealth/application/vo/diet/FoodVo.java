package com.pixelo.health.wellplate.myhealth.application.vo.diet;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FoodVo(
        String name,
        BigDecimal calorie
) {
}