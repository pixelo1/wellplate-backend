package com.pixelo.health.wellplate.membership.application.vo;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Builder
public record UserDetailVo(
        String password,
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Collection<? extends GrantedAuthority> authorities
) {
}
