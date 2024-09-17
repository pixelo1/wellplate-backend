package com.pixelo.health.wellplate.authentication.infrastructure.springrest.internal.callee;

import com.pixelo.health.wellplate.authentication.application.in.query.AuthenticationQueryInputPort;
import com.pixelo.health.wellplate.authentication.infrastructure.TokenFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TokenCalleeInternalRestAdapter implements TokenFacade {

    private final AuthenticationQueryInputPort authenticationQueryInputPort;

    @Override
    public Boolean validateToken(String token) {
        return authenticationQueryInputPort.tokenIsValid(token);
    }
}
