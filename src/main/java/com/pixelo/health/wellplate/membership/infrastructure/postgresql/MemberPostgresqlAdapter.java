package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.core.auth.UserService;
import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPostgresqlAdapter implements MemberOutputPort, UserService {

    private final MemberPostgresqlRepository memberPostgresqlRepository;

    private static User apply(Member member) {
        return new User(member.email(),
                member.password(),
                member.isEnabled(),
                member.isAccountNonExpired(),
                member.isCredentialsNonExpired(),
                member.isAccountNonLocked(),
                member.getAuthorities()
        );
    }

    @Override
    public Member save(Member member) {
        return memberPostgresqlRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var memberOptional = memberPostgresqlRepository.findByEmail(email);
        return memberOptional.map(MemberPostgresqlAdapter::apply)
                .orElseThrow(() -> new UsernameNotFoundException("유저가 없습니다."));
    }
}
