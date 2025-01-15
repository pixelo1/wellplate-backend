package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Schema(name = "식단 등록 요청")
public class CreateDietRequest {

    @NotNull
    @Schema(title = "섭취 시간", example = "2021-08-01 12:30:22")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime mealTime;

    @NotEmpty
    private List<FoodInfoRequest> foodInfos;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class FoodInfoRequest {
        @NotNull
        @Schema(title = "음식명", example = "된장찌개")
        private String foodName;

        @NotNull
        @PositiveOrZero
        @Schema(title = "칼로리", example = "204.2")
        private BigDecimal calorie;
    }
}
