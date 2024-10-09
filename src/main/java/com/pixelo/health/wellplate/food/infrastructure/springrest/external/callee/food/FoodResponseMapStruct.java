package com.pixelo.health.wellplate.food.infrastructure.springrest.external.callee.food;

import com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort.*;
import static com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort.SearchFoodsQuery;

@Mapper(componentModel = "spring")
public interface FoodResponseMapStruct {

    @Mapping(target = "foods", source = "foodVos")
    SearchedFoodsResponse toSearchedFoodsResponse(FoodVos vos);
}
