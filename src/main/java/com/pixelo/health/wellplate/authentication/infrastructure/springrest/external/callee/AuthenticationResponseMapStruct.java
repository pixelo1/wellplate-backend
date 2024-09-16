package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.response.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationResponseMapStruct {

    AuthenticationResponse toAuthenticationResponse(TokenVo tokenVo);
}
