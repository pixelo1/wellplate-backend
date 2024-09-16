package com.pixelo.health.wellplate.membership;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberFacadeVo(
        UUID memberId,
        String email,
        String password,
        String memberType
) {
}
