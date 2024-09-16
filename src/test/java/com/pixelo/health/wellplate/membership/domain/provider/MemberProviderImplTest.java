package com.pixelo.health.wellplate.membership.domain.provider;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberProviderImplTest {

    @Test
    void memberProviderImpl() {
        String email = "test@naver.com";
        String password = "1234";

        var createdMember = Member.builder()
                .email(email)
                .password(password)
                .memberType(MemberType.WELLNESS_CHALLENGER)
                .build();

        MemberEntityProvider memberProvider = MemberProviderImpl.from(createdMember);

        Assertions.assertEquals(memberProvider.getMemberId(), createdMember.memberId());
        Assertions.assertEquals(memberProvider.getEmail(), createdMember.email());
        Assertions.assertEquals(memberProvider.getPassword(), createdMember.password());
        Assertions.assertEquals(memberProvider.getMemberType(), createdMember.memberType());
    }
}