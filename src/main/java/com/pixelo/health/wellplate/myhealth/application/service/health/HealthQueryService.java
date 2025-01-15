package com.pixelo.health.wellplate.myhealth.application.service.health;

import com.pixelo.health.wellplate.myhealth.application.in.query.health.GetRegisteredHealthQuery;
import com.pixelo.health.wellplate.myhealth.application.in.query.health.HealthQueryInputPort;
import com.pixelo.health.wellplate.myhealth.application.out.HealthOutputPort;
import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVo;
import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVoMapStruct;
import com.pixelo.health.wellplate.myhealth.domain.health.Health;
import com.pixelo.health.wellplate.myhealth.domain.health.provider.HealthProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthQueryService implements HealthQueryInputPort {
    private final HealthOutputPort healthOutputPort;
    private final HealthVoMapStruct healthVoMapStruct;

    public HealthVo getRegisteredHealth(GetRegisteredHealthQuery query) {
        Health health = healthOutputPort.findByWellnessChallengerId(query.wellnessChallengerId());
        var healthProvider = HealthProviderImpl.from(health);
        return healthVoMapStruct.toHealthVo(healthProvider);
    }
}
