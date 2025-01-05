package com.pixelo.health.wellplate.authentication.domain.member;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "auth_member", schema = "wellplate")
public class AuthMember implements UserDetails {

    @Id
    private UUID memberId;

    private String loginId;

    private String password;

    private String memberType;

    @Builder
    public AuthMember(UUID memberId,
                      String loginId,
                      String password,
                      String memberType) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.password = password;
        this.memberType = memberType;
    }

    public UUID memberId() {
        return memberId;
    }

    public String loginId() {
        return loginId;
    }

    public String password() {
        return password;
    }

    public String memberType() {
        return memberType;
    }

    /**
     * authenticate를 위한 override
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 여러 권한을 가질 수 있으므로 Collection으로 반환
        var authority = new SimpleGrantedAuthority(memberType);
        var simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        simpleGrantedAuthorities.add(authority);
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    /**
     * 계정 탈퇴, 비밀번호 변경, 휴면, 락 등 정책을 추가해주면 된다
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
