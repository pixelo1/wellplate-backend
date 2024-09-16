package com.pixelo.health.wellplate.membership;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
public record UserDetailFacadeVo(
        String password,
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Collection<? extends GrantedAuthority> authorities
) {
}
