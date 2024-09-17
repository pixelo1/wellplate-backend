package com.pixelo.health.wellplate.authentication.application.out;

import com.pixelo.health.wellplate.authentication.domain.token.Token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenOutputPort {

    Token save(Token token);
    List<Token> saveAll(List<Token> tokens);
    List<Token> findAllValidTokenByMemberId(UUID memberId);

    Token findByToken(String token);
}
