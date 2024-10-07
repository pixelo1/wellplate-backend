package com.pixelo.health.wellplate.myhealth.application.service.health;

import com.pixelo.health.wellplate.myhealth.application.in.command.health.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.domain.health.domainservice.dtos.CreateHealthDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthCommandMapStruct {

    CreateHealthDto toCreateHealthDto(RegisterHealthCommand command);
}
