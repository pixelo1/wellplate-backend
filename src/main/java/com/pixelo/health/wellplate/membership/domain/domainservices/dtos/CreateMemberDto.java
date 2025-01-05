package com.pixelo.health.wellplate.membership.domain.domainservices.dtos;

import lombok.Builder;

@Builder
public record CreateMemberDto(
        String email,
        String loginId,
        String password
) {
}
