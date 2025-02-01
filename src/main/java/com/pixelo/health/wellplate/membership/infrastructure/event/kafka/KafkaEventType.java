package com.pixelo.health.wellplate.membership.infrastructure.event.kafka;

public enum KafkaEventType {
    MEMBER_CREATED;

    public String code() {
        return this.name();
    }
}
