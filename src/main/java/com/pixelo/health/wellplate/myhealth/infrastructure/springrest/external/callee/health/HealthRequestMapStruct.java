package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health;

import com.pixelo.health.wellplate.framework.spi.AuthUser;
import com.pixelo.health.wellplate.myhealth.application.in.command.health.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthRequestMapStruct {

    RegisterHealthCommand toRegisterHealthCommand(RegisterHealthRequest request, AuthUser authUser);
}
