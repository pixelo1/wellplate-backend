package com.pixelo.health.wellplate.myhealth.application.in.query.health;

import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVo;

public interface HealthQueryInputPort {

    HealthVo getRegisteredHealth(GetRegisteredHealthQuery query);
}
