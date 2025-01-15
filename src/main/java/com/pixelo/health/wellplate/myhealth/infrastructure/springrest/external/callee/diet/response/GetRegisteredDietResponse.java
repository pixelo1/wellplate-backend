package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response;

import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request.CreateDietRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class GetRegisteredDietResponse {

    private List<DietResponse> diets;


    @Getter
    @Builder
    public static class DietResponse {
        private UUID dietId;
        private LocalDateTime mealTime;
        private List<FoodInfoRequest> foodInfos;

        @Getter
        @Builder
        @AllArgsConstructor
        public static class FoodInfoRequest {
            @NotNull
            @Schema(title = "음식명", example = "된장찌개")
            private String name;

            @NotNull
            @PositiveOrZero
            @Schema(title = "칼로리", example = "204.2")
            private BigDecimal calorie;
        }
    }
}
