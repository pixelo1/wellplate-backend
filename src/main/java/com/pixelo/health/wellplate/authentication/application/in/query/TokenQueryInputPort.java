package com.pixelo.health.wellplate.authentication.application.in.query;

public interface TokenQueryInputPort {

    boolean tokenIsValid(String token);
}
