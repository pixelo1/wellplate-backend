package com.pixelo.health.wellplate.food.application.in.query;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

public interface FoodQueryInputPort {
    FoodVos searchFoods(SearchFoodsQuery query);

    @Builder
    record FoodVos(
            List<FoodVo> foodVos
    ) {}

    @Builder
    record FoodVo(
            String foodName,
            String foodCode,
            BigDecimal calorie,
            BigDecimal size,
            String sizeUnit,
            String makerName
    ) {}

    @Builder
    record SearchFoodsQuery(
            String foodName
    ) {}
}
