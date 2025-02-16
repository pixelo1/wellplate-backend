package com.pixelo.health.wellplate.framework.token;


import java.util.UUID;

public interface TokenExtractor {
    UUID extractMemberId(String token);
}
