package com.pixelo.health.wellplate.membership.domain.member.event;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberCreatedEvent(
        UUID memberId,
        String loginId,
        String password,
        String memberType
) {
}
