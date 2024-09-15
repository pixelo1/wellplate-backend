package com.pixelo.health.wellplate.membership.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberType {

    ADMIN("운영자"),
    WELLNESS_CHALLENGER("일반사용자");

    private final String label;

    public String code() {
        return name();
    }
    public String label() {
        return this.label;
    }
}
