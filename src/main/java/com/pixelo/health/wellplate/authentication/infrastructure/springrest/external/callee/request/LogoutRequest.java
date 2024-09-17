package com.pixelo.health.wellplate.authentication.infrastructure.springrest.external.callee.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {
    private String accessToken;
}
