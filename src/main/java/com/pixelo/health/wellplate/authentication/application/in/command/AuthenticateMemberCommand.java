package com.pixelo.health.wellplate.authentication.application.in.command;

import lombok.Builder;

@Builder
public record AuthenticateMemberCommand(
        String loginId,
        String password
) {
}
