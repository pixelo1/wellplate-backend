package com.pixelo.health.wellplate.authentication.application.out;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Builder
public record RegisteredUserDetailsResponse(
        UUID memberId,
        String password,
        String username,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        Collection<? extends GrantedAuthority> authorities
) {
}
