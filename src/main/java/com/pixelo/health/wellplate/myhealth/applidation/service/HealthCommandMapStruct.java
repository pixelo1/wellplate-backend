package com.pixelo.health.wellplate.myhealth.applidation.service;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.domain.health.domainservice.dtos.CreateHealthDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthCommandMapStruct {

    CreateHealthDto toCreateHealthDto(RegisterHealthCommand command);
}
