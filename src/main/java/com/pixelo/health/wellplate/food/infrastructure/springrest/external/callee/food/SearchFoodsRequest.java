package com.pixelo.health.wellplate.food.infrastructure.springrest.external.callee.food;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SearchFoodsRequest {

    @Parameter(name = "foodName", description = "식품명")
    @NotBlank
    private String foodName;
}
