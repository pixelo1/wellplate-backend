package com.pixelo.health.wellplate.myhealth.infrastructure.springrest.external.callee.diet.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreatedDietResponse {

    private UUID dietId;
}
