package com.pixelo.health.wellplate.health.infrastructure.springrest.external.callee.health.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class RegisterHealthRequest {
    @NotNull
    @Schema(title = "기준 몸무게")
    private BigDecimal baseBodyWeight;
    @NotNull
    @Schema(title = "목표 몸무게")
    private BigDecimal goalBodyWeight;
}
