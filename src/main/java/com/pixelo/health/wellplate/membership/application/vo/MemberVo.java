package com.pixelo.health.wellplate.membership.application.vo;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberVo(
        UUID memberId,
        String loginId,
        String email,
        String password,
        String memberType
) {
}
