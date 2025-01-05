package com.pixelo.health.wellplate.membership.infrastructure.postgresql;

import com.pixelo.health.wellplate.membership.application.out.MemberOutputPort;
import com.pixelo.health.wellplate.membership.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberPostgresqlAdapter implements MemberOutputPort {

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
}
