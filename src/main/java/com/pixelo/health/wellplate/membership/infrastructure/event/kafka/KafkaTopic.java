package com.pixelo.health.wellplate.membership.infrastructure.event.kafka;


public enum KafkaTopic {
    MEMBERSHIP_TOPIC;

    public String code() {
        return this.name();
    }
}
