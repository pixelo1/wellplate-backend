package com.pixelo.health.wellplate.membership.domain.member.event;

import lombok.RequiredArgsConstructor;

public enum Topic {
    MEMBERSHIP_TOPIC;

    public String code() {
        return this.name();
    }
}
