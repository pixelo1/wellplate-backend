package com.pixelo.health.wellplate.authentication.application.service;

import com.pixelo.health.wellplate.authentication.application.in.command.TokenCommandInputPort;
import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenCommandService implements TokenCommandInputPort {

    private final TokenOutputPort tokenOutputPort;

    @Override
    public void updateTokenNotUsable(String tokenParam) {
        var token = tokenOutputPort.findByToken(tokenParam);
        token.updateTokenRevoke();
        tokenOutputPort.save(token);
    }
}
