package com.pixelo.health.wellplate.membership.spi;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberRegisteredEvent(
        UUID memberId,
        String loginId,
        String password,
        String memberType
) {
}
