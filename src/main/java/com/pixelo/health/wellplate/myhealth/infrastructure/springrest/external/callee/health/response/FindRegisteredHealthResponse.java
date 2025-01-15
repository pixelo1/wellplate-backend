package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class FindRegisteredHealthResponse {
    private UUID healthId;
    private BigDecimal baseBodyWeight;
    private String baseMeasurementUnit;
    private BigDecimal goalBodyWeight;
    private String goalMeasurementUnit;
}
