package com.pixelo.health.wellplate.membership.application.out.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ExternalEventDto <T> (
        String aggregateType,
        UUID aggregateId,
        String topic,
        String eventType,
        T payload
) {
}
