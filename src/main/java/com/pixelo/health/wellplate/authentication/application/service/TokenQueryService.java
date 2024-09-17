package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.query.TokenQueryInputPort;
import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenQueryService implements TokenQueryInputPort {

    private final TokenOutputPort tokenOutputPort;
    @Override
    public boolean tokenIsValid(String tokenParam) {
        Token token = tokenOutputPort.findByToken(tokenParam);
        return token.validaToken();
    }
}
