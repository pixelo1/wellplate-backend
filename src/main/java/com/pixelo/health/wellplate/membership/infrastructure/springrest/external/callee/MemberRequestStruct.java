package com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.membership.application.in.command.RegisterMemberCommand;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.request.RegisterMemberRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberRequestStruct {

    RegisterMemberCommand toRegisterMemberCommand(RegisterMemberRequest request);
}
