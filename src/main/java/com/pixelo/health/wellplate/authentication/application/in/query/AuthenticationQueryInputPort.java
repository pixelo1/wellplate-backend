package com.pixelo.health.wellplate.authentication.application.in.query;

public interface AuthenticationQueryInputPort {

    boolean tokenIsValid(String token);
}
