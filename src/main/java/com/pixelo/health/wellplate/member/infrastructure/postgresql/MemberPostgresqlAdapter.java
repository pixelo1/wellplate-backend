package com.pixelo.health.wellplate.member.infrastructure.postgresql;

import com.pixelo.health.wellplate.member.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
