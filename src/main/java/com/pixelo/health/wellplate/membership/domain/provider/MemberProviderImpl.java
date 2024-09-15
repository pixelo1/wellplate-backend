package com.pixelo.health.wellplate.membership.domain.provider;

import com.pixelo.health.wellplate.membership.domain.Member;
import com.pixelo.health.wellplate.membership.domain.MemberType;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor(staticName = "from")
public class MemberProviderImpl implements MemberEntityProvider {

//    private final Supplier<Member> memberLoader;
    private final Member member;


    public UUID getMemberId() {
        return member.memberId();
    }

    public String getEmail() {
        return member.email();
    }

    public String getPassword() {
        return member.password();
    }

    public MemberType getMemberType() {
        return member.memberType();
    }
}
