package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.RegisterHealthCommand;
import com.pixelo.health.wellplate.myhealth.applidation.vo.HealthVo;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.request.RegisterHealthRequest;
import com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.health.response.RegisteredHealthResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HealthResponseMapStruct {

    RegisteredHealthResponse toRegisteredHealthResponse(HealthVo vo);
}
