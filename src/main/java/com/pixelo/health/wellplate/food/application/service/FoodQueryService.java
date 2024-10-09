package com.pixelo.health.wellplate.food.application.service;

import com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort;
import com.pixelo.health.wellplate.food.application.out.FoodOutputPort;
import com.pixelo.health.wellplate.food.application.vo.FoodMapStruct;
import com.pixelo.health.wellplate.food.domain.food.Food;
import com.pixelo.health.wellplate.food.domain.food.provider.FoodAdapter;
import com.pixelo.health.wellplate.food.domain.food.provider.FoodAdapterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodQueryService implements FoodQueryInputPort {

    private final FoodOutputPort foodOutputPort;
    private final FoodMapStruct foodMapStruct;

    @Override
    public FoodVos searchFoods(SearchFoodsQuery query) {
        List<Food> foods = foodOutputPort.searchFood(query.foodName());
        List<FoodAdapter> foodAdapters = foods.stream()
                .map(FoodAdapterImpl::from)
                .collect(Collectors.toList());
        return foodMapStruct.toFoodVos(foodAdapters);
    }
}
