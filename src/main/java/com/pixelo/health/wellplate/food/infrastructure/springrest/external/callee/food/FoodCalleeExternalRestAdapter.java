package com.pixelo.health.wellplate.food.infrastructure.springrest.external.callee.food;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import com.pixelo.health.wellplate.food.application.in.query.FoodQueryInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "식품 정보 조회 API")
@RequestMapping("/api/v1/foods")
@RequiredArgsConstructor
public class FoodCalleeExternalRestAdapter {

    private final FoodQueryInputPort foodQueryInputPort;
    private final FoodRequestMapStruct foodRequestMapStruct;
    private final FoodResponseMapStruct foodResponseMapStruct;

    @GetMapping
    @Operation(summary = "심품명을 검색한다")
    public ResultResponse<?> searchFoods(
            @AuthenticationPrincipal AuthUser authUser,
            @ParameterObject SearchFoodsRequest request
    ) {
        var query = foodRequestMapStruct.toSearchFoodsQuery(request);
        var foodVos = foodQueryInputPort.searchFoods(query);
        var response = foodResponseMapStruct.toSearchedFoodsResponse(foodVos);
        return ResultResponse.ok(response);
    }
}
