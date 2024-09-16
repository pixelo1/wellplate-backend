package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticateMemberCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.RegisterTokenAndMemberCommand;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.AuthenticateMemberRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.RegisterTokenAndMemberRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationRequestMapStruct {
    RegisterTokenAndMemberCommand toRegisterTokenAndMemberCommand(RegisterTokenAndMemberRequest request);
    AuthenticateMemberCommand toAuthenticateMemberCommand(AuthenticateMemberRequest request);
}
