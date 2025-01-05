package com.pixelo.health.wellplate.authentication.application.in;

public interface TokenQueryInputPort {

    boolean tokenIsValid(String token);
}
