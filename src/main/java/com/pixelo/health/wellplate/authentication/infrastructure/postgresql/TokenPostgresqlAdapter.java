package com.pixelo.health.wellplate.authentication.infrastructure.postgresql;


import com.pixelo.health.wellplate.authentication.application.out.TokenOutputPort;
import com.pixelo.health.wellplate.authentication.domain.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenPostgresqlAdapter implements TokenOutputPort {

    private final TokenPostgresqlRepository tokenPostgresqlRepository;

    @Override
    public Token save(Token token) {
        return tokenPostgresqlRepository.save(token);
    }

    @Override
    public List<Token> saveAll(List<Token> tokens) {
        return tokenPostgresqlRepository.saveAll(tokens);
    }

    @Override
    public List<Token> findAllValidTokenByMemberId(UUID memberId) {
        return tokenPostgresqlRepository.findAllValidTokenByMemberIdAndRevokedAndExpired(memberId, false, false);
    }

    @Override
    public Token findByToken(String token) {
        return tokenPostgresqlRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 토큰이 없습니다"));
    }
}
