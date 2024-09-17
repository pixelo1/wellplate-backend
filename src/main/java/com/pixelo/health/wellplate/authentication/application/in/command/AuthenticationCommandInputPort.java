package com.pixelo.health.wellplate.authentication.application.in.command;

import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;

public interface AuthenticationCommandInputPort {

    TokenVo registerTokenAndMember(RegisterTokenAndMemberCommand command);

    TokenVo authenticateMember(AuthenticateMemberCommand command);

    TokenVo refreshToken(RefreshTokenCommand command);

}
