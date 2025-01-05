package com.pixelo.health.wellplate.authentication.application.in.command.member;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SaveMemberCommand(
        UUID memberId,
        String loginId,
        String password,
        String memberType
) {
}
