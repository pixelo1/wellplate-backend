package com.pixelo.health.wellplate.membership.application.in.command;

import com.pixelo.health.wellplate.membership.application.in.vo.MemberVo;

public interface MemberInputPort {

    MemberVo registerMemberCommand(RegisterMemberCommand command);
}
