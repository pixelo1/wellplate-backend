package com.pixelo.health.wellplate.core.auth;

public class TokenExpiredException extends RuntimeException{
    private static final String message = "토큰이 만료되었습니다";
    public TokenExpiredException() {
        super(message);
    }
}
