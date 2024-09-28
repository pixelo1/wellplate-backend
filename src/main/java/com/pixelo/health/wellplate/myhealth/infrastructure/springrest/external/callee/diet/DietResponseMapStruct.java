package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.CreateDietCommand;
import com.pixelo.health.wellplate.myhealth.applidation.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.request.CreateDietRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.CreatedDietResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DietResponseMapStruct {

    CreatedDietResponse toCreatedDietResponse(DietVo vo);
}
