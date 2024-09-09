package com.pixelo.health.wellplate.member.application.in.vo;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MemberVo(
        UUID memberId,
        String email,
        String password
) {
}
