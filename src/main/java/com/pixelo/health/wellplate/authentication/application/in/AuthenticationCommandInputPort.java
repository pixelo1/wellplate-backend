package com.pixelo.health.wellplate.authentication.application.in;

import com.pixelo.health.wellplate.authentication.application.in.command.AuthenticateMemberCommand;
import com.pixelo.health.wellplate.authentication.application.in.command.RefreshTokenCommand;
import com.pixelo.health.wellplate.authentication.application.vo.TokenVo;

public interface AuthenticationCommandInputPort {

    TokenVo authenticateMember(AuthenticateMemberCommand command);

    TokenVo refreshToken(RefreshTokenCommand command);

}
