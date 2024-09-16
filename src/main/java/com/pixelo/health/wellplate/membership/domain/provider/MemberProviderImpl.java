package com.pixelo.health.wellplate.membership.domain.provider;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;


@RequiredArgsConstructor(staticName = "from")
public class MemberProviderImpl implements MemberEntityProvider {

//    private final Supplier<Member> memberLoader;
    private final Member member;


    public UUID getMemberId() {
        return member.memberId();
    }

    public String getEmail() {
        return member.email();
    }

    public String getPassword() {
        return member.password();
    }

    public MemberType getMemberType() {
        return member.memberType();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getAuthorities();
    }


    public String getUsername() {
        return member.email();
    }

    public boolean getAccountNonExpired() {
        return member.isAccountNonExpired();
    }

    public boolean getAccountNonLocked() {
        return member.isAccountNonLocked();
    }

    public boolean getCredentialsNonExpired() {
        return member.isCredentialsNonExpired();
    }

    public boolean getEnabled() {
        return member.isEnabled();
    }
}
