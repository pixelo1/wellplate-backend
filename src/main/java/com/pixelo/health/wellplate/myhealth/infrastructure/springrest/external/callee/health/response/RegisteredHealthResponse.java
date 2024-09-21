package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response;

import com.pixelo.health.wellplate.myhealth.applidation.vo.HealthVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class RegisteredHealthResponse {
    private UUID healthId;
    private UUID wellnessChallengerId;
    private BigDecimal baseBodyWeight;
    private String baseMeasurementUnit;
    private BigDecimal goalBodyWeight;
    private String goalMeasurementUnit;

    public static RegisteredHealthResponse of(HealthVo vo) {
        return RegisteredHealthResponse.builder()
                .healthId(vo.healthId())
                .wellnessChallengerId(vo.wellnessChallengerId())
                .baseBodyWeight(vo.baseBodyWeight())
                .baseMeasurementUnit(vo.baseMeasurementUnit())
                .goalBodyWeight(vo.goalBodyWeight())
                .goalMeasurementUnit(vo.goalMeasurementUnit())
                .build();
    }
}
