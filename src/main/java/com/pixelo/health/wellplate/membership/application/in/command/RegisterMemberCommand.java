package com.pixelo.health.wellplate.membership.application.in.command;

import lombok.Builder;

@Builder
public record RegisterMemberCommand(
        String email,
        String loginId,
        String password
) {
}
