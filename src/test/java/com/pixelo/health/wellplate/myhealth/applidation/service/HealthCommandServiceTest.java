package com.pixelo.health.wellplate.myhealth.applidation.service;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.health.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.applidation.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.applidation.service.health.HealthCommandMapStruct;
import com.pixelo.health.wellplate.myhealth.applidation.service.health.HealthCommandMapStructImpl;
import com.pixelo.health.wellplate.myhealth.applidation.service.health.HealthCommandService;
import com.pixelo.health.wellplate.myhealth.applidation.vo.health.HealthVoMapStruct;
import com.pixelo.health.wellplate.myhealth.applidation.vo.health.HealthVoMapStructImpl;
import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import com.pixelo.health.wellplate.myhealth.domain.health.domainservice.HealthFactory;
import com.pixelo.health.wellplate.myhealth.domain.health.provider.HealthProvider;
import com.pixelo.health.wellplate.myhealth.domain.health.provider.HealthProviderImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class HealthCommandServiceTest {

    @Mock
    private HealthOutputPort healthOutputPort;
    @Mock
    private HealthCommandMapStruct healthCommandMapStruct;
    @Mock
    private HealthVoMapStruct healthVoMapStruct;
    @InjectMocks
    private HealthCommandService healthCommandService;

    private HealthCommandMapStructImpl healthCommandMapStructImpl = new HealthCommandMapStructImpl();
    private HealthVoMapStructImpl healthVoMapStructImpl = new HealthVoMapStructImpl();

    @Test
    @DisplayName("건강 상태 등록")
    void registerHealth() {
        //given
        var command = RegisterHealthCommand.builder()
                .wellnessChallengerId(UUID.randomUUID())
                .baseBodyWeight(BigDecimal.valueOf(75))
                .goalBodyWeight(BigDecimal.valueOf(67))
                .build();
        var dto = healthCommandMapStructImpl.toCreateHealthDto(command);
        Mockito.when(healthCommandMapStruct.toCreateHealthDto(Mockito.any(RegisterHealthCommand.class)))
                .thenReturn(dto);

        var health = HealthFactory.createHealth(dto);
        Mockito.when(healthOutputPort.save(Mockito.any(Health.class)))
                .thenReturn(health);

        var healthProvider = HealthProviderImpl.from(health);
        var healthVo = healthVoMapStructImpl.toHealthVo(healthProvider);
        Mockito.when(healthVoMapStruct.toHealthVo(Mockito.any(HealthProvider.class)))
                .thenReturn(healthVo);

        //when
        var resultHealthVo = healthCommandService.registerHealthCommand(command);

        //then
        Assertions.assertThat(resultHealthVo.healthId()).isEqualTo(healthVo.healthId());
        Assertions.assertThat(resultHealthVo.wellnessChallengerId()).isEqualTo(healthVo.wellnessChallengerId());
        Assertions.assertThat(resultHealthVo.baseBodyWeight()).isEqualTo(healthVo.baseBodyWeight());
        Assertions.assertThat(resultHealthVo.baseMeasurementUnit()).isEqualTo(healthVo.baseMeasurementUnit());
        Assertions.assertThat(resultHealthVo.goalBodyWeight()).isEqualTo(healthVo.goalBodyWeight());
        Assertions.assertThat(resultHealthVo.goalMeasurementUnit()).isEqualTo(healthVo.goalMeasurementUnit());

        Mockito.verify(healthCommandMapStruct).toCreateHealthDto(Mockito.any(RegisterHealthCommand.class));
        Mockito.verify(healthOutputPort).save(Mockito.any(Health.class));
        Mockito.verify(healthVoMapStruct).toHealthVo(Mockito.any(HealthProvider.class));
    }
}