package com.pixelo.health.wellplate.membership.domain.member.event;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class MemberEvent <T> {

    private String aggregateType;
    private UUID aggregateId;
    private Topic topic;
    private EventType eventType;
    private T payload;

    @Builder
    public MemberEvent(String aggregateType,
                       UUID aggregateId,
                       Topic topic,
                       EventType eventType,
                       T payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.topic = topic;
        this.eventType = eventType;
        this.payload = payload;
    }

    public String aggregateType() {
        return aggregateType;
    }

    public UUID aggregateId() {
        return aggregateId;
    }

    public String topic() {
        return topic.code();
    }

    public String eventType() {
        return eventType.code();
    }

    public T payload() {
        return payload;
    }

}
