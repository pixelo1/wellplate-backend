package com.pixelo.health.wellplate.member.domain.domainservices.dtos;

import lombok.Builder;

@Builder
public record CreateMemberDto(
        String email,
        String password
) {
}
