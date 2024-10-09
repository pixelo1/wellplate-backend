package com.pixelo.health.wellplate.food.domain.food.provider;

import java.math.BigDecimal;

public interface FoodAdapter {
    String getFoodCode();
    String getFoodName();
    BigDecimal getCalorie();
    BigDecimal getSize();
    String getSizeUnit();
    String getMakerName();
}
