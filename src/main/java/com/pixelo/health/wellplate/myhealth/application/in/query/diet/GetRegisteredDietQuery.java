package com.pixelo.health.wellplate.myhealth.application.in.query.diet;

import lombok.Builder;

import java.util.UUID;

@Builder
public record GetRegisteredDietQuery(
        UUID wellnessChallengerId,
        UUID healthId
) {
}
