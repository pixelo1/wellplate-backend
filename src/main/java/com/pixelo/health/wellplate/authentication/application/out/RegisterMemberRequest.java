package com.pixelo.health.wellplate.authentication.application.out;

import lombok.Builder;

@Builder
public record RegisterMemberRequest(
        String email,
        String password
) {
}
