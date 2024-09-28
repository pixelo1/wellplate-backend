package com.pixelo.health.wellplate.myhealth.domain.diet.adapter;

import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor(staticName = "of")
public class FoodAdapter {
    private final Food food;

    public String getName() {
        return food.name();
    }
    public BigDecimal getCalorie() {
        return food.calorie();
    }
}
