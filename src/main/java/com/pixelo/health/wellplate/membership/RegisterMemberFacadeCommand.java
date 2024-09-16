package com.pixelo.health.wellplate.membership;

import lombok.Builder;

@Builder
public record RegisterMemberFacadeCommand(
        String email,
        String password
) {
}
