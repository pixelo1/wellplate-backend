package com.pixelo.health.wellplate.membership.application.in.command;

import com.pixelo.health.wellplate.membership.application.vo.MemberVo;

import java.util.UUID;

public interface MemberCommandInputPort {

    MemberVo registerMemberCommand(RegisterMemberCommand command);

}
