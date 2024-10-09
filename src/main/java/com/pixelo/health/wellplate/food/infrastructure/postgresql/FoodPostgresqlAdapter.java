package com.pixelo.health.wellplate.food.infrastructure.postgresql;

import com.pixelo.health.wellplate.food.application.out.FoodOutputPort;
import com.pixelo.health.wellplate.food.domain.food.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodPostgresqlAdapter implements FoodOutputPort {

    private final FoodPostgresqlRepository foodPostgresqlRepository;

    @Override
    public List<Food> searchFood(String foodName) {
        return foodPostgresqlRepository.findByFoodNameContains(foodName);
    }
}
