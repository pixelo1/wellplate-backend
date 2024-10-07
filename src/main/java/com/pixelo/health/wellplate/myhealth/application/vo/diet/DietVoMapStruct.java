package com.pixelo.health.wellplate.myhealth.application.vo.diet;

import com.pixelo.health.wellplate.myhealth.domain.diet.adapter.DietAdapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DietVoMapStruct {

    @Mapping(source = "foodAdapters", target = "foodVos")
    DietVo toDietVo(DietAdapter dietAdapter);

}
