package com.pixelo.health.wellplate.membership.domain.member.event;

public enum EventType {
    MEMBER_CREATED;

    public String code() {
        return this.name();
    }
}
