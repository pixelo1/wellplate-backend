package com.pixelo.health.wellplate.food.application.vo;

import com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort;
import com.pixelo.health.wellplate.food.domain.food.provider.FoodAdapter;
import com.pixelo.health.wellplate.food.domain.food.provider.FoodAdapterImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapStruct {

    FoodQueryInputPort.FoodVo toFoodVo(FoodAdapter foodAdapter);

    List<FoodQueryInputPort.FoodVo> toFoodVoList(List<FoodAdapter> foodAdapters);

    default FoodQueryInputPort.FoodVos toFoodVos(List<FoodAdapter> foodAdapters) {
        return FoodQueryInputPort.FoodVos.builder()
                .foodVos(toFoodVoList(foodAdapters))
                .build();
    }

}
