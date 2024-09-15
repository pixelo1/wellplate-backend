package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPostgresqlAdapter implements MemberOutputPort {

    private final MemberPostgresqlRepository memberPostgresqlRepository;

    @Override
    public Member save(Member member) {
        var savedMember = memberPostgresqlRepository.save(member);
        return savedMember;
    }
}
