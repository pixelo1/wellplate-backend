package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.core.spi.JwtUserDetails;
import com.pixelo.health.wellplate.core.spi.UserService;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberPostgresqlAdapter implements MemberOutputPort, UserService {

    private final MemberPostgresqlRepository memberPostgresqlRepository;

    private static JwtUserDetails apply(Member member) {
        return JwtUserDetails.builder()
                .memberId(member.memberId())
                .password(member.password())
                .username(member.email())
                .accountNonExpired(member.isAccountNonExpired())
                .accountNonLocked(member.isAccountNonLocked())
                .credentialsNonExpired(member.isCredentialsNonExpired())
                .enabled(member.isEnabled())
                .authorities(member.getAuthorities())
                .build();
    }

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

    /**
     * Security
     * */
    @Override
    public JwtUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var memberOptional = memberPostgresqlRepository.findByEmail(email);
        return memberOptional.map(MemberPostgresqlAdapter::apply)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));
    }

    @Override
    public JwtUserDetails findUserById(UUID memberId) throws UsernameNotFoundException {
        var memberOptional = memberPostgresqlRepository.findById(memberId);
        return memberOptional.map(MemberPostgresqlAdapter::apply)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));
    }

}
