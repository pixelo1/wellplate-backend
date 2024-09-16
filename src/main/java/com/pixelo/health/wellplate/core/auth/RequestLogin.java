package com.pixelo.health.wellplate.core.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record RequestLogin(
        @NotNull
        String email,
        @NotNull
        String password
) {
}
