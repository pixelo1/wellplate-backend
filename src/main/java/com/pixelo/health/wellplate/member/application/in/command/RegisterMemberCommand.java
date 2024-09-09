package com.pixelo.health.wellplate.member.application.in.command;

import lombok.Builder;

@Builder
public record RegisterMemberCommand(
        String email,
        String password
) {
}
