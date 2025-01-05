package com.pixelo.health.wellplate.authentication.infrastructure.postgresql;

import com.pixelo.health.wellplate.authentication.application.out.AuthMemberOutputPort;
import com.pixelo.health.wellplate.authentication.domain.member.AuthMember;
import com.pixelo.health.wellplate.core.spi.JwtUserDetails;
import com.pixelo.health.wellplate.core.spi.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthMemberPostgresqlAdapter implements AuthMemberOutputPort, UserService {

    private final AuthMemberPostgresqlRepository authMemberPostgresqlRepository;

    @Override
    public AuthMember save(AuthMember authMember) {
        return authMemberPostgresqlRepository.save(authMember);
    }

    @Override
    public AuthMember findMemberByMemberId(UUID memberId) {
        return authMemberPostgresqlRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다"));
    }

    @Override
    public AuthMember findMemberByLoginId(String loginId) {
        return authMemberPostgresqlRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다"));
    }


    /**
     * Security
     * */
    @Override
    public JwtUserDetails findUserById(UUID memberId) {
        AuthMember authMember = findMemberByMemberId(memberId);
        return apply(authMember);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthMember authMember = findMemberByLoginId(username);
        return apply(authMember);
    }

    private JwtUserDetails apply(AuthMember authMember) {
        return JwtUserDetails.builder()
                .memberId(authMember.memberId())
                .password(authMember.password())
                .username(authMember.loginId())
                .accountNonExpired(authMember.isAccountNonExpired())
                .accountNonLocked(authMember.isAccountNonLocked())
                .credentialsNonExpired(authMember.isCredentialsNonExpired())
                .enabled(authMember.isEnabled())
                .authorities(authMember.getAuthorities())
                .build();
    }
}
