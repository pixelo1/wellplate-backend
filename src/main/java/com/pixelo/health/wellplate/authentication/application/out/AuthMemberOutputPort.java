package com.pixelo.health.wellplate.authentication.application.out;

import com.pixelo.health.wellplate.authentication.domain.member.AuthMember;

import java.util.UUID;

public interface AuthMemberOutputPort {

    AuthMember save(AuthMember authMember);

    AuthMember findMemberByMemberId(UUID memberId);

    AuthMember findMemberByLoginId(String loginId);
}
