package com.pixelo.health.wellplate.myhealth.application.service.diet;

import com.pixelo.health.wellplate.myhealth.application.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.domain.diet.domainservice.dto.CreateDietDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DietCommandMapStruct {

    @Mapping(target = "createFoodInfoDtos", source = "foodInfos")
    CreateDietDto toCreateDietDto(CreateDietCommand command);
}
