package com.pixelo.health.wellplate.membership.domain.member;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public enum RegistrationStatus {

    PENDING("진행중"),
    CANCELLED("취소"),
    COMPLETED("완료"),
    ;


    private final String label;

    public String code() {
        return name();
    }
    public String label() {
        return this.label;
    }

}
