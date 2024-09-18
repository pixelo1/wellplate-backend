package com.pixelo.health.wellplate.membership.application.in.command;

import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;

import java.util.UUID;

public interface MemberCommandInputPort {

    MemberShipVo registerMemberCommand(RegisterMemberCommand command);
//    MemberShipVo findMemberByEmail(String email);
//    MemberShipVo findMemberById(UUID memberId);

}
