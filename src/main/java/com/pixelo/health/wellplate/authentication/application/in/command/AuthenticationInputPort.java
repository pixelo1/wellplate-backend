package com.pixelo.health.wellplate.authentication.application.in.command;

import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;

public interface AuthenticationInputPort {

    TokenVo registerTokenAndMember(RegisterTokenAndMemberCommand command);

    void authenticateMember(AuthenticateMemberCommand command);

    void refreshToken();
}
