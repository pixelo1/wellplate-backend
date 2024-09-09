package com.pixelo.health.wellplate.member.application.in.command;

import com.pixelo.health.wellplate.member.application.in.vo.MemberVo;

public interface MemberInputPort {

    MemberVo registerMemberCommand(RegisterMemberCommand command);
}
