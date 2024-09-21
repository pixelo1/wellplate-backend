package com.pixelo.health.wellplate.core.auth;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Supplier;

@Component
public class AuthUserContext {

    private static final Supplier<Authentication> authUserContextProvider = () -> SecurityContextHolder.getContext().getAuthentication();

    public AuthUser authUser() {
        if (authUserContextProvider.get().getPrincipal() instanceof AuthUser) {
            return (AuthUser) authUserContextProvider.get().getPrincipal();
        }
        return AuthUser.builder().build(); // 로그인 안한경우
    }

    public UUID userId() {
        return authUser().wellnessChallengerId();
    }
}
