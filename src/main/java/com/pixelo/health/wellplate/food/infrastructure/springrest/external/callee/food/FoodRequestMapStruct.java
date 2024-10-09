package com.pixelo.health.wellplate.food.infrastructure.springrest.external.callee.food;

import com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort.*;

@Mapper(componentModel = "spring")
public interface FoodRequestMapStruct {

    SearchFoodsQuery toSearchFoodsQuery(SearchFoodsRequest request);
}
