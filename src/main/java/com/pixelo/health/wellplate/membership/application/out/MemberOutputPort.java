package com.pixelo.health.wellplate.membership.application.out;

import com.pixelo.health.wellplate.membership.domain.Member;

import java.util.function.Supplier;

public interface MemberOutputPort {

    Member save(Member member);
}
