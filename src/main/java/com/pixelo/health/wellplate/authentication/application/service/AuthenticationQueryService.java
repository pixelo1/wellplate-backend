package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.query.AuthenticationQueryInputPort;
import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationQueryService implements AuthenticationQueryInputPort {

    private final TokenOutputPort tokenOutputPort;

    @Override
    public boolean tokenIsValid(String tokenPram) {
        var token = tokenOutputPort.findByToken(tokenPram);
        return token.validaToken();
    }
}
