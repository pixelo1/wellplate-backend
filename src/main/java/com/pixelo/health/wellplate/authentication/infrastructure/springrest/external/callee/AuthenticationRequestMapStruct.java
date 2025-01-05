package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticateMemberCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.RefreshTokenCommand;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.AuthenticateMemberRequest;
import com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request.RefreshTokenRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationRequestMapStruct {
    AuthenticateMemberCommand toAuthenticateMemberCommand(AuthenticateMemberRequest request);
    RefreshTokenCommand toRefreshTokenCommand(RefreshTokenRequest request);
}
