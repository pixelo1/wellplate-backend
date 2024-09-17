package com.pixelo.health.wellplate.authentication.infrastructure;

public interface TokenFacade {

    Boolean validateToken(String token);
}
