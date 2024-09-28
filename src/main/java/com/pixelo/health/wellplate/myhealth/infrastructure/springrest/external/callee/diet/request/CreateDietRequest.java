package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Schema(name = "식단 등록 요청")
public class CreateDietRequest {

    @Schema(title = "건강 상태 id")
    private UUID healthId;

    @Schema(title = "섭취 시간", example = "2024-01-02")
    private LocalDate mealTime;

    private List<FoodInfoRequest> foodInfos;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FoodInfoRequest {
        @Schema(title = "음식명", example = "된장찌개")
        private String foodName;
        @Schema(title = "칼로리", example = "204.2")
        private BigDecimal calorie;
    }
}
