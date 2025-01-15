package com.pixelo.health.wellplate.myhealth.application.in.query.health;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GetRegisteredHealthQuery(
        UUID wellnessChallengerId
) {
}
