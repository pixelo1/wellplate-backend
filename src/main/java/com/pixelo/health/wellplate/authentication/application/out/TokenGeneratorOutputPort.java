package com.pixelo.health.wellplate.authentication.application.out;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;
import java.util.function.Function;

public interface TokenGeneratorOutputPort {

    UUID extractMemberId(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    <T extends UserDetails> String generateToken(T userDetails);
    <T extends UserDetails> String generateRefreshToken(T userDetails);
    boolean isTokenExpired(String token);
}
