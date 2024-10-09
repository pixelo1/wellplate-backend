package com.pixelo.health.wellplate.food.infrastructure.springrest.external.callee.food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

import static com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort.*;

@Getter
@AllArgsConstructor
@Builder
public class SearchedFoodsResponse {

    List<FoodResponse> foods;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FoodResponse {
        @Schema(title = "식품명")
        String foodName;
        @Schema(title = "식품코드")
        String foodCode;
        @Schema(title = "열량(칼로리)")
        BigDecimal calorie;
        @Schema(title = "식품중량")
        BigDecimal size;
        @Schema(title = "식품중량 - 단위")
        String sizeUnit;
        @Schema(title = "제조사")
        String makerName;

    }


}
