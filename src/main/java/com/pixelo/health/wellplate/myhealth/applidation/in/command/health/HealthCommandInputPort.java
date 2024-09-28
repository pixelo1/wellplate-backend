package com.pixelo.health.wellplate.myhealth.applidation.in.command.health;

import com.pixelo.health.wellplate.myhealth.applidation.vo.health.HealthVo;

public interface HealthCommandInputPort {
    HealthVo registerHealthCommand(RegisterHealthCommand command);
}
