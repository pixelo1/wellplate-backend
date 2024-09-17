package com.pixelo.health.wellplate.membership.application.in.command;

import com.pixelo.health.wellplate.membership.application.vo.MemberShipVo;
import com.pixelo.health.wellplate.membership.application.vo.MemberVo;

public interface MemberInputPort {

    MemberShipVo registerMemberCommand(RegisterMemberCommand command);
    MemberShipVo findMemberByEmail(String email);
}
