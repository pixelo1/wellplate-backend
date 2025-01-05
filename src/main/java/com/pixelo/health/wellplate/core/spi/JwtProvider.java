package com.pixelo.health.wellplate.core.spi;

import com.pixelo.health.wellplate.core.auth.DateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

//todo 인터페이스 제공으로 변경 필요
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    private final DateProvider dateProvider;

    public UUID extractMemberId(String token) {
        String stringMemberId = extractClaim(token, Claims::getSubject);
        return UUID.fromString(stringMemberId);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public <T extends UserDetails> String generateToken(T userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private <T extends UserDetails> String generateToken(Map<String, Object> extraClaims, T userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public <T extends UserDetails> String generateRefreshToken(T userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration);
    }

    private <T extends UserDetails> String buildToken(
            Map<String, Object> extraClaims,
            T userDetails,
            long expiration
    ) {
        var jwtUserDetails = (JwtUserDetails) userDetails;
        return Jwts.builder()
                .claims(extraClaims)
                .subject(String.valueOf(jwtUserDetails.memberId())) //todo memberId가 있어야 하지 않을까?
                .issuedAt(dateProvider.currentTimeMillis())
                .expiration(dateProvider.currentTimeMillisPlusMillis(expiration))
                .signWith(getSignInKey())
                .compact();
    }

//    public boolean isTokenUserNameValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return username.equals(userDetails.getUsername());
//    }

    public boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSignInKey() {
        //interface 제공 random key 사용해보자 -
//        return Jwts.SIG.HS256.key().build();
        //인코딩해둔 secretkey를 평문으로 사용하기 위함
        try {
            //todo ref
            byte[] digest = MessageDigest.getInstance("SHA-256").digest(secretKey.getBytes());
            return Keys.hmacShaKeyFor(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
