package com.pixelo.health.wellplate.member.application.out;

import com.pixelo.health.wellplate.member.domain.Member;

import java.util.Optional;

public interface MemberOutputPort {

    Member save(Member member);
}
