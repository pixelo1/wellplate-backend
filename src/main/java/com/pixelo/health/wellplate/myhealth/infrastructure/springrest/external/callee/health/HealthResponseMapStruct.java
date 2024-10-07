package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health;

import com.pixelo.health.wellplate.myhealth.application.vo.health.HealthVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response.RegisteredHealthResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthResponseMapStruct {

    RegisteredHealthResponse toRegisteredHealthResponse(HealthVo vo);
}
