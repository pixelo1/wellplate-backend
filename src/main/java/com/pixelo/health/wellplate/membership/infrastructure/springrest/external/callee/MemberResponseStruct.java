package com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee;

import com.pixelo.health.wellplate.membership.application.vo.MemberVo;
import com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.response.RegisteredMemberResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberResponseStruct {

    RegisteredMemberResponse toRegisteredMemberResponse(MemberVo vo);
}
