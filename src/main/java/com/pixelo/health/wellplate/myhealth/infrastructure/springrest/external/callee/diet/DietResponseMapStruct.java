package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet;

import com.pixelo.health.wellplate.myhealth.application.vo.diet.DietVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.CreatedDietResponse;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response.GetRegisteredDietResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DietResponseMapStruct {

    CreatedDietResponse toCreatedDietResponse(DietVo vo);
    @Mapping(source = "foodVos", target = "foodInfos")
    GetRegisteredDietResponse.DietResponse toGetRegisteredDietResponseDietResponse(DietVo vo);
}
