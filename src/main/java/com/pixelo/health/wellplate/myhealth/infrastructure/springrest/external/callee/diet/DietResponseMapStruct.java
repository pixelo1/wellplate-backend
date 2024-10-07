package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.CreatedDietResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DietResponseMapStruct {

    CreatedDietResponse toCreatedDietResponse(DietVo vo);
}
