package com.pixelo.health.wellplate.myhealth.application.service.health;

import com.pixelo.health.wellplate.myhealth.application.in.command.health.HealthCommandInputPort;
import com.pixelo.health.wellplate.myhealth.application.in.command.health.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.application.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVo;
import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVoMapStruct;
import com.pixelo.health.wellplate.myhealth.domain.health.domainservice.HealthFactory;
import com.pixelo.health.wellplate.myhealth.domain.health.provider.HealthProviderImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthCommandService implements HealthCommandInputPort {

    private final HealthOutputPort healthOutputPort;
    private final HealthCommandMapStruct healthCommandMapStruct;
    private final HealthVoMapStruct healthVoMapStruct;

    @Override
    public HealthVo registerHealthCommand(RegisterHealthCommand command) {
        var createHealthDto = healthCommandMapStruct.toCreateHealthDto(command);
        var health = HealthFactory.createHealth(createHealthDto);
        var healthProvider = HealthProviderImpl.from(healthOutputPort.save(health));
        return healthVoMapStruct.toHealthVo(healthProvider);
    }
}
