package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.framework.spi.JwtUserDetails;
import com.pixelo.health.wellplate.framework.spi.UserService;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberPostgresqlAdapter implements MemberOutputPort, UserService {

    private final MemberPostgresqlRepository memberPostgresqlRepository;

    @Override
    public Member save(Member member) {
        return memberPostgresqlRepository.save(member);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberPostgresqlRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
    }
    @Override
    public Member findMemberById(UUID memberId) {
        return memberPostgresqlRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
    }

    @Override
    public JwtUserDetails findUserById(UUID memberId) {
        var member = findMemberById(memberId);
        return apply(member);
    }

    private JwtUserDetails apply(Member member) {
        return JwtUserDetails.builder()
                .memberId(member.memberId())
                .password(member.password())
                .username(member.loginId())
                .accountNonExpired(member.isAccountNonExpired())
                .accountNonLocked(member.isAccountNonLocked())
                .credentialsNonExpired(member.isCredentialsNonExpired())
                .enabled(member.isEnabled())
                .authorities(member.getAuthorities())
                .build();
    }
}
