package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class RegisterHealthRequest {
    @NotNull
    @Schema(title = "기준 몸무게")
    private BigDecimal baseBodyWeight;
    @NotNull
    @Schema(title = "목표 몸무게")
    private BigDecimal goalBodyWeight;
}
