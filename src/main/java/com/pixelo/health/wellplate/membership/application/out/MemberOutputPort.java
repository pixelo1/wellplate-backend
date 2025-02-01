package com.pixelo.health.wellplate.membership.application.out;

import com.pixelo.health.wellplate.membership.domain.member.Member;

import java.util.UUID;

public interface MemberOutputPort {

    Member save(Member member);
    Member findMemberByEmail(String email);
    Member findMemberById(UUID memberId);
}
