package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.HealthCommandInputPort;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.applidation.vo.HealthVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.HealthRequestMapStruct;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.HealthRequestMapStructImpl;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.HealthResponseMapStruct;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.HealthResponseMapStructImpl;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HealthCalleeExternalRestAdapterTest {

    @Mock
    private HealthRequestMapStruct healthRequestMapStruct;
    @Mock
    private HealthResponseMapStruct healthResponseMapStruct;
    @Mock
    private HealthCommandInputPort healthCommandInputPort;
    @InjectMocks
    private HealthCalleeExternalRestAdapter healthCalleeExternalRestAdapter;

    private static HealthRequestMapStructImpl requestMapStructImpl;
    private static HealthResponseMapStructImpl responseMapStructImpl;
    private static AuthUser authUser;
    private static RegisterHealthRequest registerHealthRequest;
    private static HealthVo healthVo;


    @BeforeAll
    static void setup() {
        final BigDecimal baseBodyWeight = BigDecimal.valueOf(100);
        final BigDecimal goalBodyWeight = BigDecimal.valueOf(80);
        final UUID healthId = UUID.randomUUID();
        final UUID wellnessChallengerId = UUID.randomUUID();
        final String measurementUnit = "kg";
        requestMapStructImpl = new HealthRequestMapStructImpl();
        responseMapStructImpl = new HealthResponseMapStructImpl();

        healthVo = HealthVo.builder()
                .healthId(healthId)
                .wellnessChallengerId(wellnessChallengerId)
                .baseBodyWeight(baseBodyWeight)
                .baseMeasurementUnit(measurementUnit)
                .goalBodyWeight(goalBodyWeight)
                .goalMeasurementUnit(measurementUnit)
                .build();
        registerHealthRequest = RegisterHealthRequest.builder()
                .baseBodyWeight(baseBodyWeight)
                .goalBodyWeight(goalBodyWeight)
                .build();
        authUser = AuthUser.builder()
                .wellnessChallengerId(wellnessChallengerId)
                .build();
    }

    @Test
    @DisplayName("건강 상태 등록 - 현재 몸무게, 목표 몸무게")
    void registerHealth() {
        //given
        var command = requestMapStructImpl.toRegisterHealthCommand(registerHealthRequest, authUser);
        Mockito.when(healthRequestMapStruct.toRegisterHealthCommand(any(RegisterHealthRequest.class), any(AuthUser.class)))
                .thenReturn(command);

        Mockito.when(healthCommandInputPort.registerHealthCommand(any(RegisterHealthCommand.class)))
                .thenReturn(healthVo);

        var response = responseMapStructImpl.toRegisteredHealthResponse(healthVo);
        Mockito.when(healthResponseMapStruct.toRegisteredHealthResponse(any(HealthVo.class)))
                .thenReturn(response);
        //when
        var result = healthCalleeExternalRestAdapter.registerHealth(authUser, registerHealthRequest);

        //then
        assertThat(result.data()).isNotNull();
        assertThat(result.data().getHealthId()).isEqualTo(healthVo.healthId());
        assertThat(result.data().getWellnessChallengerId()).isEqualTo(authUser.wellnessChallengerId());
        assertThat(result.data().getBaseBodyWeight()).isEqualTo(healthVo.baseBodyWeight());

        verify(healthRequestMapStruct).toRegisterHealthCommand(any(RegisterHealthRequest.class), any(AuthUser.class));
        verify(healthCommandInputPort).registerHealthCommand(any(RegisterHealthCommand.class));
        verify(healthResponseMapStruct).toRegisteredHealthResponse(any(HealthVo.class));
    }
}