package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.myhealth.application.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request.CreateDietRequest;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DietRequestMapStruct {

    CreateDietCommand toCreateDietCommand(CreateDietRequest request, AuthUser authUser, UUID healthId);
}
