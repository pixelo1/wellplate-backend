package com.pixelo.health.wellplate.authentication.infrastructure.springrest.internal.callee;

import com.pixelo.health.wellplate.authentication.spi.TokenFacade;
import com.pixelo.health.wellplate.authentication.application.in.query.TokenQueryInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TokenCalleeInternalRestAdapter implements TokenFacade {

    private final TokenQueryInputPort tokenQueryInputPort;

    @Override
    public Boolean validateToken(String token) {
        return tokenQueryInputPort.tokenIsValid(token);
    }
}
