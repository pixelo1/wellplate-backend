package com.pixelo.health.wellplate.myhealth.applidation.service.diet;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DietCommandMapStruct {

    @Mapping(target = "createFoodInfoDtos", source = "foodInfos")
    CreateDietDto toCreateDietDto(CreateDietCommand command);
}