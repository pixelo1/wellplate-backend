package com.pixelo.health.wellplate.food.domain.food.provider;

import com.pixelo.health.wellplate.food.domain.food.Food;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor(staticName = "from")
public class FoodAdapterImpl implements FoodAdapter {
    private final Food food;

    @Override
    public String getFoodCode() {
        return food.foodCode();
    }

    @Override
    public String getFoodName() {
        return food.foodName();
    }

    @Override
    public BigDecimal getCalorie() {
        return food.calorie();
    }

    @Override
    public BigDecimal getSize() {
        return food.size();
    }

    @Override
    public String getSizeUnit() {
        return food.sizeUnit();
    }

    @Override
    public String getMakerName() {
        return food.makerName();
    }
}
