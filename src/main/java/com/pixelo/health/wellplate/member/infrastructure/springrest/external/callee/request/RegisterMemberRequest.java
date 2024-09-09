package com.pixelo.health.wellplate.member.infrastructure.springrest.external.callee.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterMemberRequest {
    private String email;
    private String password;
}
