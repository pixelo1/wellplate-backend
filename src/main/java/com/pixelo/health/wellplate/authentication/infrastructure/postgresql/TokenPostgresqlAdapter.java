package com.pixelo.health.wellplate.authentication.infrastructure.postgresql;


import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenPostgresqlAdapter implements TokenOutputPort {

    private final TokenPostgresqlRepository tokenPostgresqlRepository;

    @Override
    public Token save(Token token) {
        return tokenPostgresqlRepository.save(token);
    }
}
