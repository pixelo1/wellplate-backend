package com.pixelo.health.wellplate.membership;

import com.pixelo.health.wellplate.core.spi.ResultResponse;

import java.util.UUID;

public interface MemberFacade {

    ResultResponse<MemberShipFacadeVo> registerMember(RegisterMemberFacadeCommand command);
    ResultResponse<MemberShipFacadeVo> findMemberByEmail(String email);
    ResultResponse<MemberShipFacadeVo> findMemberById(UUID memberId);
}
