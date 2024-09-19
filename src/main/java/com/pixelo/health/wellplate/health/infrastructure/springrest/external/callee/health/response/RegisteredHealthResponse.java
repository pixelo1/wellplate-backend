package com.pixelo.health.wellplate.health.infrastructure.springrest.external.callee.health.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RegisteredHealthResponse {

    private UUID healthId;
}
