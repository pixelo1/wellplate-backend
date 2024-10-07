package com.pixelo.health.wellplate.myhealth.application.in.command.health;

import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVo;

public interface HealthCommandInputPort {
    HealthVo registerHealthCommand(RegisterHealthCommand command);
}
