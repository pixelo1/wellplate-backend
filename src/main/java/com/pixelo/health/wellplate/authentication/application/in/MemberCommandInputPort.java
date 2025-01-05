package com.pixelo.health.wellplate.authentication.application.in;

import com.pixelo.health.wellplate.authentication.application.in.command.member.SaveMemberCommand;

public interface MemberCommandInputPort {

    void saveMember(SaveMemberCommand command);
}
