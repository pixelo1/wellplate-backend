package com.pixelo.health.wellplate.membership.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

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

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authority = new SimpleGrantedAuthority("ROLE_" + this.code());
        var simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        simpleGrantedAuthorities.add(authority);
        return simpleGrantedAuthorities;
    }
}
