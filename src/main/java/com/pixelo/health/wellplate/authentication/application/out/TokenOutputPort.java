package com.pixelo.health.wellplate.authentication.application.out;

import com.pixelo.health.wellplate.authentication.domain.token.Token;

public interface TokenOutputPort {

    Token save(Token token);
}
