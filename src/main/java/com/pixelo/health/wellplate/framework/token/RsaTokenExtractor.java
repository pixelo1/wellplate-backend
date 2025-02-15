package com.pixelo.health.wellplate.framework.token;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RsaTokenExtractor implements TokenExtractor {


    @Override
    public UUID extractMemberId(String token) {
        String stringMemberId = extractClaim(token, JWTClaimsSet::getSubject);
        return UUID.fromString(stringMemberId);
    }

    private <T> T extractClaim(String token, Function<JWTClaimsSet, T> claimsResolver) {
        try {
            JWTClaimsSet claims = getAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JWT claims", e);
        }
    }

    private JWTClaimsSet getAllClaims(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet();
    }
}
