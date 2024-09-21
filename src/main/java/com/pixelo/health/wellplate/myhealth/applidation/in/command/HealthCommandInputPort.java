package com.pixelo.health.wellplate.myhealth.applidation.in.command;

import com.pixelo.health.wellplate.myhealth.applidation.vo.HealthVo;

public interface HealthCommandInputPort {
    HealthVo registerHealthCommand(RegisterHealthCommand command);
}
