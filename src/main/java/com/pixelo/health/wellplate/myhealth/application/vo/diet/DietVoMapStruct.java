package com.pixelo.health.wellplate.myhealth.application.vo.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.Diet;
import com.pixelo.health.wellplate.myhealth.domain.diet.valueobjects.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DietVoMapStruct {

    @Mapping(target = "foodVos", source = "foods")
    DietVo toDietVo(Diet diet, List<Food> foods, BigDecimal totalCalories);
    List<FoodVo> toFoodVos(List<Food> foods);

    default DietVo toDietVo(Diet diet) {
        return toDietVo(diet, diet.foods(), diet.totalCalories());
    }
}
