package com.pixelo.health.wellplate.food.application.out;

import com.pixelo.health.wellplate.food.domain.food.Food;

import java.util.List;

public interface FoodOutputPort {

    List<Food> searchFood(String foodName);
}
