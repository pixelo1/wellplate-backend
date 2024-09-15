package com.pixelo.health.wellplate.membership.infrastructure.springrest.external.callee.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RegisteredMemberResponse {
    UUID memberId;
    String email;
    String password;
    String memberType;

}
