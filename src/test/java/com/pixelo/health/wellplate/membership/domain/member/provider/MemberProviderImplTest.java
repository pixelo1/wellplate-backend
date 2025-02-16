package com.pixelo.health.wellplate.membership.domain.member.provider;

import com.pixelo.health.wellplate.membership.domain.member.Member;
import com.pixelo.health.wellplate.membership.domain.member.MemberType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberProviderImplTest {

    @Test
    void memberProviderImpl() {
        String loginId = "test";
        String email = "test@naver.com";
        String password = "1234";

        var createdMember = Member.builder()
                .loginId(loginId)
                .email(email)
                .password(password)
                .memberType(MemberType.ROLE_WELLNESS_CHALLENGER)
                .build();

        MemberEntityProvider memberProvider = MemberProviderImpl.from(createdMember);

        Assertions.assertEquals(memberProvider.getMemberId(), createdMember.memberId());
        Assertions.assertEquals(memberProvider.getEmail(), createdMember.email());
        Assertions.assertEquals(memberProvider.getPassword(), createdMember.password());
        Assertions.assertEquals(memberProvider.getMemberType(), createdMember.memberType());
    }
}